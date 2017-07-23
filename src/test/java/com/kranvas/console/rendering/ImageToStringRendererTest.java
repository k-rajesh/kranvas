package com.kranvas.console.rendering;

import com.kranvas.core.Image;
import com.kranvas.core.Pixel;
import com.kranvas.core.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ImageToStringRendererTest {
    private static final String BLANK_2_BY_3_IMAGE =
            "----" + System.lineSeparator() +
            "|  |" + System.lineSeparator() +
            "|  |" + System.lineSeparator() +
            "|  |" + System.lineSeparator() +
            "----" + System.lineSeparator();

    private static final String SQUARE_INSIDE_SQUARE =
            "--------" + System.lineSeparator() +
            "|      |" + System.lineSeparator() +
            "| xxx  |" + System.lineSeparator() +
            "| x x  |" + System.lineSeparator() +
            "| xxx  |" + System.lineSeparator() +
            "|      |" + System.lineSeparator() +
            "|      |" + System.lineSeparator() +
            "--------" + System.lineSeparator();

    private static final String FULL_IMAGE =
            "--------" + System.lineSeparator() +
            "|xxxxxx|" + System.lineSeparator() +
            "|xxxxxx|" + System.lineSeparator() +
            "|xxxxxx|" + System.lineSeparator() +
            "|xxxxxx|" + System.lineSeparator() +
            "|xxxxxx|" + System.lineSeparator() +
            "|xxxxxx|" + System.lineSeparator() +
            "--------" + System.lineSeparator();

    private ImageToStringRenderer sut = new ImageToStringRenderer();
    private Pixel linePixel = mock(Pixel.class);

    @BeforeEach
    void setUp() {
        when(linePixel.getColor()).thenReturn('x');
    }

    @Test
    void null_image_produces_null_result() {
        assertNull(sut.render(null));
    }

    @Test
    void blank_2_by_3() {
        Image image = mockImage(2, 3);
        assertEquals(BLANK_2_BY_3_IMAGE, sut.render(image));
    }

    @Test
    void square_inside_square() {
        Image image = mockImage(6, 6);
        when(image.getPixel(any())).thenAnswer(i -> {
            Point point = i.getArgumentAt(0, Point.class);
            return (
                    (point.getX() >= 1 && point.getX() <= 3) &&
                    (point.getY() >= 1 && point.getY() <= 3) &&
                    !(point.getY() == 2 && point.getX() == 2)
                   ) ? linePixel : null;
        });
        assertEquals(SQUARE_INSIDE_SQUARE, sut.render(image));
    }

    @Test
    void full_image() {
        Image image = mockImage(6, 6);
        when(image.getPixel(any())).thenReturn(linePixel);
        assertEquals(FULL_IMAGE, sut.render(image));
    }

    private Image mockImage(int width, int height) {
        Image image = mock(Image.class);
        when(image.getWidth()).thenReturn(width);
        when(image.getHeight()).thenReturn(height);
        when(image.getPixel(any())).thenReturn(null);
        return image;
    }
}
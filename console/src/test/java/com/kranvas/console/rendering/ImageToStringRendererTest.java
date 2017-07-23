package com.kranvas.console.rendering;

import com.kranvas.core.Canvas;
import com.kranvas.core.Image;
import com.kranvas.core.Pixel;
import com.kranvas.core.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.any;
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

    private final CanvasConsoleRenderer sut = new CanvasConsoleRenderer();

    @Mock
    private Pixel linePixel;

    @Mock private Image image;

    @Mock private Canvas canvas;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(canvas.getImage()).thenReturn(image);
        when(linePixel.getColor()).thenReturn('x');
    }

    @Test
    void null_canvas_produces_null_result() {
        assertNull(sut.render(null));
    }

    @Test
    void blank_2_by_3() {
        setUpImage(2, 3);
        assertEquals(BLANK_2_BY_3_IMAGE, sut.render(canvas));
    }

    @Test
    void square_inside_square() {
        setUpImage(6, 6);
        when(image.getPixel(any())).thenAnswer(i -> {
            Point point = i.getArgumentAt(0, Point.class);
            return (
                    (point.getX() >= 1 && point.getX() <= 3) &&
                    (point.getY() >= 1 && point.getY() <= 3) &&
                    !(point.getY() == 2 && point.getX() == 2)
                   ) ? linePixel : null;
        });
        assertEquals(SQUARE_INSIDE_SQUARE, sut.render(canvas));
    }

    @Test
    void full_image() {
        setUpImage(6, 6);
        when(image.getPixel(any())).thenReturn(linePixel);
        assertEquals(FULL_IMAGE, sut.render(canvas));
    }

    private void setUpImage(int width, int height) {
        when(image.getWidth()).thenReturn(width);
        when(image.getHeight()).thenReturn(height);
        when(image.getPixel(any())).thenReturn(null);
    }
}
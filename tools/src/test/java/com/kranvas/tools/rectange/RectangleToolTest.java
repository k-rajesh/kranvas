package com.kranvas.tools.rectange;

import com.kranvas.core.Image;
import com.kranvas.core.Pixel;
import com.kranvas.core.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class RectangleToolTest {
    private static final int IMAGE_WIDTH = 10;
    private static final int IMAGE_HEIGHT = 20;
    private static final char EXPECTED_LINE_COLOR = 'x';

    private RectangleTool sut = new RectangleTool();

    private Image mockImage;
    private Pixel mockPixel;

    @BeforeEach
    void setUp() {
        mockImage = mock(Image.class);
        when(mockImage.getWidth()).thenReturn(IMAGE_WIDTH);
        when(mockImage.getHeight()).thenReturn(IMAGE_HEIGHT);

        mockPixel = mock(Pixel.class);
        when(mockImage.getPixel(any())).thenReturn(mockPixel);
    }

    @Test
    void single_point_rectangle() {
        sut.perform(mockImage, new RectangleToolParams(Point.at(1, 1), Point.at(1, 1)));
        verify(mockImage, times(1)).getPixel(Point.at(1, 1));
        verify(mockPixel, times(1)).setColor(EXPECTED_LINE_COLOR);
    }

    @Test
    void normal_order_of_points() {
        sut.perform(mockImage, new RectangleToolParams(Point.at(1, 1), Point.at(2, 2)));
        verify2By2RectangleDrawn(Point.at(1, 1), Point.at(2, 1), Point.at(1, 2), Point.at(2, 2));
    }

    @Test
    void reverse_order_of_points() {
        sut.perform(mockImage, new RectangleToolParams(Point.at(2, 2), Point.at(1, 1)));
        verify2By2RectangleDrawn(Point.at(1, 1), Point.at(2, 1), Point.at(1, 2), Point.at(2, 2));
    }

    private void verify2By2RectangleDrawn(Point topLeft, Point topRight, Point bottomLeft, Point bottomRight) {
        verify(mockImage, times(1)).getPixel(topLeft);
        verify(mockImage, times(1)).getPixel(topRight);
        verify(mockImage, times(1)).getPixel(bottomLeft);
        verify(mockImage, times(1)).getPixel(bottomRight);

        verify(mockPixel, times(4)).setColor(EXPECTED_LINE_COLOR);
    }

    private void verify4By4RectangleDrawn(Point topLeft, Point topRight, Point bottomLeft, Point bottomRight) {
        verify(mockImage, times(1)).getPixel(topLeft);
        verify(mockImage, times(1)).getPixel(topRight);
        verify(mockImage, times(1)).getPixel(bottomLeft);
        verify(mockImage, times(1)).getPixel(bottomRight);

        verify(mockPixel, times(4*4)).setColor(EXPECTED_LINE_COLOR);
    }

    @Test
    void normal_4_by_4_rectangle() {
        sut.perform(mockImage, new RectangleToolParams(Point.at(1, 1), Point.at(4, 4)));

        // Verify top horizontal line
        for(int x=1; x <= 4; x++)
            verify(mockImage, times(1)).getPixel(Point.at(x, 1));

        // Verify bottom horizontal line
        for(int x=1; x <= 4; x++)
            verify(mockImage, times(1)).getPixel(Point.at(x, 4));

        // Verify left vertical line
        for(int y=2; y <= 3; y++)
            verify(mockImage, times(1)).getPixel(Point.at(1, y));

        // Verify right vertical line
        for(int y=2; y <= 3; y++)
            verify(mockImage, times(1)).getPixel(Point.at(4, y));
    }

    @Test
    void null_image_throws_exception() {
        verifyNullException(() -> sut.perform(null, new RectangleToolParams(Point.at(1, 1), Point.at(1, 1))));
    }

    @Test
    void null_params_throws_exception() {
        verifyNullException(() -> sut.perform(mockImage, null));
    }

    @Test
    void coordinates_out_of_bounds() {
        assertThrows(IllegalArgumentException.class, () -> sut.perform(mockImage, new RectangleToolParams(Point.at(1, IMAGE_HEIGHT+1), Point.at(1, 1))));
    }

    private void verifyNullException(Executable executable) {
        Throwable exception = assertThrows(IllegalArgumentException.class, executable);
        assertTrue(exception.getMessage().contains("null"));
    }
}
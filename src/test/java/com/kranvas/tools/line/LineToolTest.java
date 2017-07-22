package com.kranvas.tools.line;

import com.kranvas.image.Image;
import com.kranvas.image.Pixel;
import com.kranvas.image.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import sun.text.CodePointIterator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class LineToolTest {
    private static final int IMAGE_WIDTH = 10;
    private static final int IMAGE_HEIGHT = 20;
    private static final char EXPECTED_LINE_COLOR = 'x';

    private LineTool sut = new LineTool();

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
    void normal_vertical_line() {
        sut.perform(mockImage, new LineToolParams(Point.at(1, 1), Point.at(1, 2)));
        verify(mockImage, times(1)).getPixel(Point.at(1, 1));
        verify(mockImage, times(1)).getPixel(Point.at(1, 2));
        verify(mockPixel, times(2)).setColor(EXPECTED_LINE_COLOR);
    }

    @Test
    void normal_horizontal_line() {
        sut.perform(mockImage, new LineToolParams(Point.at(1, 1), Point.at(2, 1)));
        verify(mockImage, times(1)).getPixel(Point.at(1, 1));
        verify(mockImage, times(1)).getPixel(Point.at(2, 1));
        verify(mockPixel, times(2)).setColor(EXPECTED_LINE_COLOR);
    }

    @Test
    void reverse_vertical_line() {
        sut.perform(mockImage, new LineToolParams(Point.at(1, 2), Point.at(1, 1)));
        verify(mockImage, times(1)).getPixel(Point.at(1, 1));
        verify(mockImage, times(1)).getPixel(Point.at(1, 2));
        verify(mockPixel, times(2)).setColor(EXPECTED_LINE_COLOR);
    }

    @Test
    void reverse_horizontal_line() {
        sut.perform(mockImage, new LineToolParams(Point.at(2, 1), Point.at(1, 1)));
        verify(mockImage, times(1)).getPixel(Point.at(1, 1));
        verify(mockImage, times(1)).getPixel(Point.at(2, 1));
        verify(mockPixel, times(2)).setColor(EXPECTED_LINE_COLOR);
    }

    @Test
    void single_point_line() {
        sut.perform(mockImage, new LineToolParams(Point.at(2, 2), Point.at(2, 2)));
        verify(mockImage, times(1)).getPixel(Point.at(2, 2));
        verify(mockPixel, times(1)).setColor(EXPECTED_LINE_COLOR);
    }

    @Test
    void null_image_throws_exception() {
        verifyNullException(() -> sut.perform(null, new LineToolParams(Point.at(1, 1), Point.at(1, 1))));
    }

    @Test
    void null_params_throws_exception() {
        verifyNullException(() -> sut.perform(mockImage, null));
    }

    private void verifyNullException(Executable executable) {
        Throwable exception = assertThrows(IllegalArgumentException.class, executable);
        assertTrue(exception.getMessage().contains("null"));
    }
}

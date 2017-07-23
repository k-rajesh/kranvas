package com.kranvas.tools.bucketfill;

import com.kranvas.core.Image;
import com.kranvas.core.Pixel;
import com.kranvas.core.Point;
import com.kranvas.tools.rectange.RectangleTool;
import com.kranvas.tools.rectange.RectangleToolParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class BucketFillToolTest {
    private static final int IMAGE_WIDTH = 10;
    private static final int IMAGE_HEIGHT = 20;
    private static final char FILL_COLOR = 'x';
    private static final char BLANK_PIXEL_COLOR = ' ';

    private BucketFillTool sut = new BucketFillTool();

    private Image mockImage;
    private Pixel mockPixel;

    @BeforeEach
    void setUp() {
        mockImage = mock(Image.class);
        when(mockImage.getWidth()).thenReturn(IMAGE_WIDTH);
        when(mockImage.getHeight()).thenReturn(IMAGE_HEIGHT);

        mockPixel = mock(Pixel.class);
        when(mockPixel.getColor()).thenReturn(BLANK_PIXEL_COLOR);
        when(mockImage.getPixel(any())).thenReturn(mockPixel);
    }

    @Test
    void empty_image() {
        sut.perform(mockImage, new BucketFillToolParams(Point.at(1, 1), FILL_COLOR));
        verify(mockPixel, times(IMAGE_HEIGHT * IMAGE_WIDTH)).setColor(FILL_COLOR);
    }

    @Test
    void null_image_throws_exception() {
        verifyNullException(() -> sut.perform(null, new BucketFillToolParams(Point.at(1, 1), FILL_COLOR)));
    }

    @Test
    void null_params_throws_exception() {
        verifyNullException(() -> sut.perform(mockImage, null));
    }

    @Test
    void coordinates_out_of_bounds() {
        assertThrows(IllegalArgumentException.class, () -> sut.perform(mockImage, new BucketFillToolParams(Point.at(1, IMAGE_HEIGHT+1), FILL_COLOR)));
    }

    private void verifyNullException(Executable executable) {
        Throwable exception = assertThrows(IllegalArgumentException.class, executable);
        assertTrue(exception.getMessage().contains("null"));
    }
}
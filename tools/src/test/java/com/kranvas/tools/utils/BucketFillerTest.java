package com.kranvas.tools.utils;

import com.kranvas.core.Image;
import com.kranvas.core.Pixel;
import com.kranvas.core.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class BucketFillerTest {
    private static final char EMPTY_PIXEL_COLOR = ' ';
    private static final char FULL_PIXEL_COLOR = 'o';

    @Mock private Image image;
    @Mock private Pixel emptyPixel;
    @Mock private Pixel fullPixel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(image.getWidth()).thenReturn(2);
        when(image.getHeight()).thenReturn(2);
        when(image.getPixel(any())).thenReturn(emptyPixel);
        when(emptyPixel.getColor()).thenReturn(EMPTY_PIXEL_COLOR);
        when(fullPixel.getColor()).thenReturn(FULL_PIXEL_COLOR);
    }

    @Test
    void null_image_is_rejected() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new BucketFiller(null, Point.at(1, 1), EMPTY_PIXEL_COLOR, FULL_PIXEL_COLOR));
        assertTrue(exception.getMessage().contains("null"));
    }

    @Test
    void null_point_is_rejected() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new BucketFiller(image, null, EMPTY_PIXEL_COLOR, FULL_PIXEL_COLOR));
        assertTrue(exception.getMessage().contains("null"));
    }

    @Test
    void empty_image() {
        new BucketFiller(image, Point.at(0, 0), EMPTY_PIXEL_COLOR, FULL_PIXEL_COLOR).fill();
        verify(emptyPixel, times(4)).setColor(FULL_PIXEL_COLOR);
    }

    @Test
    void full_image() {
        when(image.getPixel(any())).thenReturn(fullPixel);
        new BucketFiller(image, Point.at(0, 0), EMPTY_PIXEL_COLOR, FULL_PIXEL_COLOR).fill();
        verify(fullPixel, times(0)).setColor(FULL_PIXEL_COLOR);
    }

    @Test
    void fill_inside_a_square() {
        when(image.getWidth()).thenReturn(10);
        when(image.getHeight()).thenReturn(10);

        // Verify top horizontal line
        for(int x=1; x <= 4; x++)
            when(image.getPixel(Point.at(x, 1))).thenReturn(fullPixel);

        // Verify bottom horizontal line
        for(int x=1; x <= 4; x++)
            when(image.getPixel(Point.at(x, 4))).thenReturn(fullPixel);

        // Verify left vertical line
        for(int y=2; y <= 3; y++)
            when(image.getPixel(Point.at(1, y))).thenReturn(fullPixel);

        // Verify right vertical line
        for(int y=2; y <= 3; y++)
            when(image.getPixel(Point.at(4, y))).thenReturn(fullPixel);

        new BucketFiller(image, Point.at(2, 2), EMPTY_PIXEL_COLOR, FULL_PIXEL_COLOR).fill();
        verify(emptyPixel, times(4)).setColor(FULL_PIXEL_COLOR);
    }
}
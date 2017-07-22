package com.kranvas.tools.utils;

import com.kranvas.image.Image;
import com.kranvas.image.Pixel;
import com.kranvas.image.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class RectangleDrawerTest {
    private Image image;

    @BeforeEach
    void setUp() {
        image = mock(Image.class);
        Pixel pixel = mock(Pixel.class);
        when(image.getPixel(any())).thenReturn(pixel);
    }

    @Test
    void draw_2_by_3_rectange() {
        RectangleDrawer.drawRectangle(image, Point.at(0, 0), Point.at(1, 2));
        verify(image, times(2*3)).getPixel(any());
    }
}
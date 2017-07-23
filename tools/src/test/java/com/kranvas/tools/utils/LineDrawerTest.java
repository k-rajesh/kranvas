package com.kranvas.tools.utils;

import com.kranvas.core.Image;
import com.kranvas.core.Pixel;
import com.kranvas.core.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class LineDrawerTest {
    private static final Point STARTING_POINT = Point.at(1, 2);
    private static final int LINE_LENGTH = 2;
    private Image image;

    @BeforeEach
    void setUp() {
        image = mock(Image.class);
        Pixel pixel = mock(Pixel.class);
        when(image.getPixel(any())).thenReturn(pixel);
    }

    @Test
    void draw_horizontal_line() {
        LineDrawer.drawHorizontalLine(image, STARTING_POINT, LINE_LENGTH);
        verify(image, times(1)).getPixel(STARTING_POINT);
        verify(image, times(1)).getPixel(Point.at(STARTING_POINT.getX()+1, STARTING_POINT.getY()));
        verifyNoMoreInteractions(image);
    }

    @Test
    void draw_vertical_line() {
        LineDrawer.drawVerticalLine(image, STARTING_POINT, LINE_LENGTH);
        verify(image, times(1)).getPixel(STARTING_POINT);
        verify(image, times(1)).getPixel(Point.at(STARTING_POINT.getX(), STARTING_POINT.getY()+1));
        verifyNoMoreInteractions(image);
    }
}
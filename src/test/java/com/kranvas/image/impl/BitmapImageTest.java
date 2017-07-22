package com.kranvas.image.impl;

import com.kranvas.image.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class BitmapImageTest {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 10;

    private BitmapImage sut = new BitmapImage(WIDTH, HEIGHT);

    @Test
    void get_width() {
        assertEquals(WIDTH, sut.getWidth());
    }

    @Test
    void get_height() {
        assertEquals(HEIGHT, sut.getHeight());
    }

    @Test
    void get_pixel() {
        assertNotNull(sut.getPixel(Point.at(2, 2)));
    }

    @Test
    void pixel_out_of_bounds() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sut.getPixel(Point.at(WIDTH, HEIGHT)));
        assertTrue(exception.getMessage().contains("outside the valid range"));
    }

    @Test
    void negative_width_throws_exception() {
        verifyNegativeNumberException(() -> new BitmapImage(-WIDTH, HEIGHT));
    }

    @Test
    void negative_height_throws_exception() {
        verifyNegativeNumberException(() -> new BitmapImage(WIDTH, -HEIGHT));
    }

    private void verifyNegativeNumberException(Executable executable) {
        Throwable exception = assertThrows(IllegalArgumentException.class, executable);
        assertTrue(exception.getMessage().contains("is negative"));
    }
}
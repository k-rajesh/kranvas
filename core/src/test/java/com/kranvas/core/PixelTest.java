package com.kranvas.core;

import com.kranvas.core.Pixel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PixelTest {
    @Test
    void set_followed_by_get() {
        Pixel pixel = new Pixel('x');
        pixel.setColor('a');
        assertEquals('a', pixel.getColor());
    }

    @Test
    void get_without_setting() {
        Pixel pixel = new Pixel('x');
        assertEquals('x', pixel.getColor());
    }
}
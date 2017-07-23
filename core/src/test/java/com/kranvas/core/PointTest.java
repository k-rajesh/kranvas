package com.kranvas.core;

import com.kranvas.core.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private static final int X = 1;
    private static final int Y = 2;
    private static final int ZERO = 0;

    @Test
    void normal_point() {
        Point point = Point.at(X, Y);
        assertEquals(X, point.getX());
        assertEquals(Y, point.getY());
    }

    @Test
    void zero_zero_is_accepted() {
        Point point = Point.at(ZERO, ZERO);
        assertEquals(ZERO, point.getX());
        assertEquals(ZERO, point.getY());
    }

    @Test
    void negative_x_coordinate_throws_exception() {
        verifyNegativeNumberException(() -> Point.at(-X, Y));
    }

    @Test
    void negative_y_coordinate_throws_exception() {
        verifyNegativeNumberException(() -> Point.at(X, -Y));
    }

    private void verifyNegativeNumberException(Executable executable) {
        Throwable exception = assertThrows(IllegalArgumentException.class, executable);
        assertTrue(exception.getMessage().contains("must be non-negative"));
    }
}
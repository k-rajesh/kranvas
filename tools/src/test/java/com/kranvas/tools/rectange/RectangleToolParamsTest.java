package com.kranvas.tools.rectange;

import com.kranvas.core.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleToolParamsTest {
    private static final Point TOP_LEFT = Point.at(1, 2);
    private static final Point BOTTOM_RIGHT = Point.at(5, 6);

    @Test
    void points_in_correct_order() {
        RectangleToolParams params = new RectangleToolParams(TOP_LEFT, BOTTOM_RIGHT);
        assertEquals(TOP_LEFT, params.getTopLeft());
        assertEquals(BOTTOM_RIGHT, params.getBottomRight());
    }

    @Test
    void points_in_reverse_order() {
        RectangleToolParams params = new RectangleToolParams(BOTTOM_RIGHT, TOP_LEFT);
        assertEquals(TOP_LEFT, params.getTopLeft());
        assertEquals(BOTTOM_RIGHT, params.getBottomRight());
    }

    @Test
    void from_and_to_points_are_the_same() {
        RectangleToolParams params = new RectangleToolParams(TOP_LEFT, TOP_LEFT);
        assertEquals(TOP_LEFT, params.getTopLeft());
        assertEquals(TOP_LEFT, params.getBottomRight());
    }

    @Test
    void points_cannot_be_null() {
        assertThrows(IllegalArgumentException.class, () -> new RectangleToolParams(null, null));
    }
}
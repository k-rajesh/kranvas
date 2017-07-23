package com.kranvas.tools.line;

import com.kranvas.core.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineToolParamsTest {
    private static final Point FROM = Point.at(1, 2);
    private static final Point TO = Point.at(5, 6);

    @Test
    void points_in_correct_order() {
        LineToolParams params = new LineToolParams(FROM, TO);
        assertEquals(FROM, params.getFrom());
        assertEquals(TO, params.getTo());
    }

    @Test
    void points_in_reverse_order() {
        LineToolParams params = new LineToolParams(TO, FROM);
        assertEquals(FROM, params.getFrom());
        assertEquals(TO, params.getTo());
    }

    @Test
    void from_and_to_points_are_the_same() {
        LineToolParams params = new LineToolParams(FROM, FROM);
        assertEquals(FROM, params.getFrom());
        assertEquals(FROM, params.getTo());
    }

    @Test
    void points_cannot_be_null() {
        assertThrows(IllegalArgumentException.class, () -> new LineToolParams(null, null));
    }
}
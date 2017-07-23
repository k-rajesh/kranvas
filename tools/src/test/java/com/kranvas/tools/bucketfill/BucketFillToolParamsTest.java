package com.kranvas.tools.bucketfill;

import com.kranvas.core.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BucketFillToolParamsTest {
    @Test
    void points_cannot_be_null() {
        assertThrows(IllegalArgumentException.class, () -> new BucketFillToolParams(null, 'c'));
    }

    @Test
    void fill_color_cannot_be_null() {
        assertThrows(IllegalArgumentException.class, () -> new BucketFillToolParams(Point.at(1, 1), null));
    }
}
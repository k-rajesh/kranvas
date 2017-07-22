package com.kranvas.validations.impl;

import com.kranvas.image.Image;
import com.kranvas.image.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PointWithinImageValidationTest {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 10;

    private Image image;

    @BeforeEach
    void setUp() {
        image = mock(Image.class);
        when(image.getWidth()).thenReturn(WIDTH);
        when(image.getHeight()).thenReturn(HEIGHT);
    }

    @Test
    void point_is_null() {
        assertFalse(PointWithinImageValidation.of(image, null).validate().isValid());
    }

    @Test
    void image_is_null() {
        assertFalse(PointWithinImageValidation.of(null, Point.at(2, 2)).validate().isValid());
    }

    @Test
    void point_well_within_image() {
        assertTrue(PointWithinImageValidation.of(image, Point.at(2, 2)).validate().isValid());
    }

    @Test
    void point_on_left_edge_is_accepted() {
        assertTrue(PointWithinImageValidation.of(image, Point.at(0, 2)).validate().isValid());
    }

    @Test
    void point_on_right_edge_is_accepted() {
        assertTrue(PointWithinImageValidation.of(image, Point.at(4, 2)).validate().isValid());
    }

    @Test
    void point_on_top_edge_is_accepted() {
        assertTrue(PointWithinImageValidation.of(image, Point.at(4, 0)).validate().isValid());
    }

    @Test
    void point_on_bottom_edge_is_accepted() {
        assertTrue(PointWithinImageValidation.of(image, Point.at(4, 9)).validate().isValid());
    }

    @Test
    void point_to_right_of_image_is_rejected() {
        assertFalse(PointWithinImageValidation.of(image, Point.at(20, 3)).validate().isValid());
    }

    @Test
    void point_below_bottom_edge_is_rejected() {
        assertFalse(PointWithinImageValidation.of(image, Point.at(4, 15)).validate().isValid());
    }
}
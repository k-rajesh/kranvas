package com.kranvas.validations.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberWithinRangeValidationTest {
    @Test
    void number_well_within_range() {
        assertTrue(NumberWithinRangeValidation.of(5, 0, 10).validate().isValid());
    }

    @Test
    void left_inclusive_is_accepted() {
        assertTrue(NumberWithinRangeValidation.of(5, 5, 10).validate().isValid());
    }

    @Test
    void right_exclusive_is_rejected() {
        assertFalse(NumberWithinRangeValidation.of(10, 0, 10).validate().isValid());
    }

    @Test
    void left_outer_range_is_rejected() {
        assertFalse(NumberWithinRangeValidation.of(3, 5, 10).validate().isValid());
    }

    @Test
    void right_outer_range_is_rejected() {
        assertFalse(NumberWithinRangeValidation.of(13, 5, 10).validate().isValid());
    }
}
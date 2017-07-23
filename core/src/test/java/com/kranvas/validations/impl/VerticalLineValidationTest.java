package com.kranvas.validations.impl;

import com.kranvas.core.Point;
import com.kranvas.validations.ValidationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VerticalLineValidationTest {
    @Test
    void positive_case() {
        ValidationResult result = VerticalLineValidation.of(Point.at(1, 1), Point.at(1, 5)).validate();
        assertTrue(result.isValid());
    }

    @Test
    void negative_case() {
        ValidationResult result = VerticalLineValidation.of(Point.at(0, 1), Point.at(5, 5)).validate();
        assertFalse(result.isValid());
    }

    @Test
    void from_is_null() {
        ValidationResult result = VerticalLineValidation.of(null, Point.at(2, 2)).validate();
        assertFalse(result.isValid());
    }

    @Test
    void to_is_null() {
        ValidationResult result = VerticalLineValidation.of(Point.at(1, 1), null).validate();
        assertFalse(result.isValid());
    }
}
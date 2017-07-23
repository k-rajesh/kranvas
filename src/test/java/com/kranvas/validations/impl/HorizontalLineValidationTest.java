package com.kranvas.validations.impl;

import com.kranvas.core.Point;
import com.kranvas.validations.ValidationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalLineValidationTest {
    @Test
    void positive_case() {
        ValidationResult result = HorizontalLineValidation.of(Point.at(0, 1), Point.at(5, 1)).validate();
        assertTrue(result.isValid());
    }

    @Test
    void negative_case() {
        ValidationResult result = HorizontalLineValidation.of(Point.at(0, 1), Point.at(5, 5)).validate();
        assertFalse(result.isValid());
    }

    @Test
    void from_is_null() {
        ValidationResult result = HorizontalLineValidation.of(null, Point.at(2, 2)).validate();
        assertFalse(result.isValid());
    }

    @Test
    void to_is_null() {
        ValidationResult result = HorizontalLineValidation.of(Point.at(1, 1), null).validate();
        assertFalse(result.isValid());
    }
}
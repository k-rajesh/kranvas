package com.kranvas.validations.impl;

import com.kranvas.image.Point;
import com.kranvas.validations.ValidationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineValidationTest {

    @Test
    void positive_case() {
        ValidationResult result = LineValidation.of(Point.at(1, 1), Point.at(2, 2)).validate();
        assertTrue(result.isValid());
    }

    @Test
    void from_is_null() {
        ValidationResult result = LineValidation.of(null, Point.at(2, 2)).validate();
        assertFalse(result.isValid());
    }

    @Test
    void to_is_null() {
        ValidationResult result = LineValidation.of(Point.at(1, 1), null).validate();
        assertFalse(result.isValid());
    }
}
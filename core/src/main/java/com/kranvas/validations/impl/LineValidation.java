package com.kranvas.validations.impl;

import com.kranvas.core.Point;
import com.kranvas.validations.Validation;
import com.kranvas.validations.ValidationResult;

/**
 * Validates that the given two points represent a line
 */
public class LineValidation extends Validation {
    Point from, to;

    LineValidation(Point from, Point to) {
        this.from = from;
        this.to = to;
    }

    public static LineValidation of(Point from, Point to) {
        return new LineValidation(from, to);
    }

    @Override
    public ValidationResult validate() {
        if (from == null)
            return ValidationResult.failure("From point is null");

        if (to == null)
            return ValidationResult.failure("To point is null");

        return ValidationResult.OK;
    }
}

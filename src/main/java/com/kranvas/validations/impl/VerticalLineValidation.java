package com.kranvas.validations.impl;

import com.kranvas.core.Point;
import com.kranvas.validations.ValidationResult;

/**
 * Validates that the given two points represent a vertical line
 */
public class VerticalLineValidation extends LineValidation {
    private VerticalLineValidation(Point from, Point to) {
        super(from, to);
    }

    public static VerticalLineValidation of(Point from, Point to) {
        return new VerticalLineValidation(from, to);
    }

    @Override
    public ValidationResult validate() {
        ValidationResult result = super.validate();
        if (!result.isValid())
            return result;

        if (from.getX() != to.getX())
            return ValidationResult.failure(String.format("The given points %s, %s do not represent a vertical line", from, to));

        return ValidationResult.OK;
    }
}

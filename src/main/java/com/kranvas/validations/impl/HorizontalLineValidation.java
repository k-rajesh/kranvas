package com.kranvas.validations.impl;

import com.kranvas.core.Point;
import com.kranvas.validations.ValidationResult;

/**
 * Validates that the given two points represent a horizontal line
 */
public class HorizontalLineValidation extends LineValidation {
    private HorizontalLineValidation(Point from, Point to) {
        super(from, to);
    }

    public static HorizontalLineValidation of(Point from, Point to) {
        return new HorizontalLineValidation(from, to);
    }

    @Override
    public ValidationResult validate() {
        ValidationResult result = super.validate();
        if (!result.isValid())
            return result;

        if (from.getY() != to.getY())
            return ValidationResult.failure(String.format("The given points %s, %s do not represent a horizontal line", from, to));

        return ValidationResult.OK;
    }
}

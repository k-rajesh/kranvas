package com.kranvas.validations.impl;

import com.kranvas.validations.Validation;
import com.kranvas.validations.ValidationResult;

public class NumberWithinRangeValidation extends Validation {
    private final int value;
    private final int leftInclusive, rightExclusive;

    private NumberWithinRangeValidation(int value, int leftInclusive, int rightExclusive) {
        this.value = value;
        this.leftInclusive = leftInclusive;
        this.rightExclusive = rightExclusive;
    }

    public static NumberWithinRangeValidation of(int value, int leftInclusive, int rightExclusive) {
        return new NumberWithinRangeValidation(value, leftInclusive, rightExclusive);
    }

    @Override
    public ValidationResult validate() {
        if (value < leftInclusive || value >= rightExclusive)
            return ValidationResult.failure(String.format("The given value %d, is outside the valid range of [%d, %d}", value, leftInclusive, rightExclusive));

        return ValidationResult.OK;
    }
}

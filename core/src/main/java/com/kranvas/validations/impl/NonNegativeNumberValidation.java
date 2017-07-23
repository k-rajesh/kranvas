package com.kranvas.validations.impl;

import com.kranvas.validations.Validation;
import com.kranvas.validations.ValidationResult;

/**
 * Verifies that the given number is non-negative
 */
public class NonNegativeNumberValidation extends Validation {
    private final int candidate;

    private NonNegativeNumberValidation(int candidate) {
        this.candidate = candidate;
    }

    public static NonNegativeNumberValidation of(int candidate) {
        return new NonNegativeNumberValidation(candidate);
    }

    @Override
    public ValidationResult validate() {
        if (candidate < 0)
            return ValidationResult.failure("Number " + candidate + " is negative");

        return ValidationResult.OK;
    }
}

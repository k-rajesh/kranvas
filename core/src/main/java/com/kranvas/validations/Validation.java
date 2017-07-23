package com.kranvas.validations;

/**
 * Represents a validation of a single requirement
 */
public abstract class Validation {
    /**
     * Performs the validation
     * @return the result of the validation
     */
    public abstract ValidationResult validate();

    /**
     * Returns a validation that allows for nested chain of validation in AND mode
     * @param validations the list of nested validations
     * @return The validation object that is the parent
     */
    public static Validation chainOfAnd(Validation... validations) {
        return new ValidateAllChain(validations);
    }

    /**
     * Returns a validation that allows for nested chain of validation in OR mode
     * @param validations the list of nested validations
     * @return The validation object that is the parent
     */
    public static Validation chainOfAny(Validation... validations) {
        return new ValidateAnyChain(validations);
    }
}

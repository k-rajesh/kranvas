package com.kranvas.validations;

/**
 * Represents an OR chain
 */
public class ValidateAnyChain extends ValidationChain {
    private static final String MESSAGE_DELIMITER = ", ";

    ValidateAnyChain(Validation... validations) {
        super(validations);
    }

    @Override
    public ValidationResult validate() {
        if (chain.isEmpty())
            return ValidationResult.OK;

        StringBuilder sb = new StringBuilder("At least one of the following conditions is required to be true: ");
        for(Validation validation : chain) {
            ValidationResult result = validation.validate();
            if (result.isValid())
                return result;

            sb.append(result.getReason()).append(MESSAGE_DELIMITER);
        }

        return ValidationResult.failure(sb.toString());
    }
}
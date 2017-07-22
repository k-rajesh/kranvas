package com.kranvas.validations;

/**
 * Represents an AND chain
 */
public class ValidateAllChain extends ValidationChain {
    ValidateAllChain(Validation... validations) {
        super(validations);
    }

    @Override
    public ValidationResult validate() {
        ValidationResult result = ValidationResult.OK;
        for(Validation validation : chain) {
            result = validation.validate();
            if (!result.isValid())
                break;
        }
        return result;
    }
}

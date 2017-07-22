package com.kranvas.validations;

/**
 * Represents the result of a validation
 */
public class ValidationResult {
    private boolean isValid;
    private String reason;

    public static final ValidationResult OK = new ValidationResult(true, "");
    public static ValidationResult failure(String reason) {
        return new ValidationResult(false, reason);
    }

    private ValidationResult(boolean isValid, String reason) {
        this.isValid = isValid;
        this.reason = reason;
    }

    /**
     * Whether the validation was successful
     * @return true if success, false if failure
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * The reason for the failure, if any
     * @return string containing the reason for failure
     */
    public String getReason() {
        return reason;
    }
}

package com.kranvas.validations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Performs a series of validations in the given order
 */
public abstract class ValidationChain extends Validation {
    List<Validation> chain = new ArrayList<>();

    public ValidationChain(Validation... validations) {
        chain.addAll(Arrays.asList(validations));
    }
}

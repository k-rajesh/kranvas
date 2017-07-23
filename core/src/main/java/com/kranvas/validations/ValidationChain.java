package com.kranvas.validations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Performs a series of validations in the given order
 */
abstract class ValidationChain extends Validation {
    List<Validation> chain = new ArrayList<>();

    ValidationChain(Validation... validations) {
        chain.addAll(Arrays.asList(validations));
    }
}

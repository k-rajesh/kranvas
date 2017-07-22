package com.kranvas.validations.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonNegativeNumberValidationTest {

    @Test
    void positive_is_accepted() {
        assertTrue(NonNegativeNumberValidation.of(10).validate().isValid());
    }

    @Test
    void negative_is_rejected() {
        assertFalse(NonNegativeNumberValidation.of(-10).validate().isValid());
    }

    @Test
    void zero_is_accepted() {
        assertTrue(NonNegativeNumberValidation.of(0).validate().isValid());
    }

}
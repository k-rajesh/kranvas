package com.kranvas.validations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ValidateChainTest {
    private Validation alwaysTrue;
    private Validation alwaysFalse;

    @BeforeEach
    void setUp() {
        alwaysTrue = mock(Validation.class);
        when(alwaysTrue.validate()).thenReturn(ValidationResult.OK);

        alwaysFalse = mock(Validation.class);
        when(alwaysFalse.validate()).thenReturn(ValidationResult.failure("Something failed"));
    }

    @Test
    void and_chain_returns_true_when_all_are_true() {
        assertTrue(Validation.chainOfAnd(alwaysTrue, alwaysTrue, alwaysTrue).validate().isValid());
        verify(alwaysTrue, times(3)).validate();
    }

    @Test
    void and_chain_terminates_on_first_encounter_of_false() {
        assertFalse(Validation.chainOfAnd(alwaysTrue, alwaysFalse, alwaysTrue).validate().isValid());
        verify(alwaysTrue, times(1)).validate();
        verify(alwaysFalse, times(1)).validate();
    }

    @Test
    void or_chain_returns_true_when_all_are_true() {
        assertTrue(Validation.chainOfAny(alwaysTrue, alwaysTrue, alwaysTrue).validate().isValid());
        verify(alwaysTrue, times(1)).validate();
    }

    @Test
    void or_chain_terminates_on_first_encounter_of_true() {
        assertTrue(Validation.chainOfAny(alwaysFalse, alwaysTrue, alwaysTrue).validate().isValid());
        verify(alwaysTrue, times(1)).validate();
        verify(alwaysFalse, times(1)).validate();
    }

    @Test
    void or_chain_returns_false_when_all_are_false() {
        assertFalse(Validation.chainOfAny(alwaysFalse, alwaysFalse, alwaysFalse).validate().isValid());
        verify(alwaysFalse, times(3)).validate();
    }
}
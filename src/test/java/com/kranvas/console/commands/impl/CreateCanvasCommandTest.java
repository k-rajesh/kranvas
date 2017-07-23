package com.kranvas.console.commands.impl;

import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.core.Canvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreateCanvasCommandTest {
    private CreateCanvasCommand sut = new CreateCanvasCommand();
    @Mock private ExecutionContext executionContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void params_not_provided() {
        verifyIllegarlArugmentException(null, "Too few");
    }

    @Test
    void empty_params_provided() {
        verifyIllegarlArugmentException("", "Too few");
    }

    @Test
    void fewer_params_than_required() {
        verifyIllegarlArugmentException("1", "Too few");
    }

    @Test
    void params_with_unexpected_letters() {
        verifyIllegarlArugmentException("1 a 2", "Too many");
    }

    @Test
    void too_many_params_than_required() {
        verifyIllegarlArugmentException("1 2 3", "Too many");
    }

    @Test
    void width_beyond_limit_than_required() {
        verifyIllegarlArugmentException("100 1", "size should be between");
    }

    @Test
    void height_beyond_limit_than_required() {
        verifyIllegarlArugmentException("1 100", "size should be between");
    }

    @Test
    void width_is_zero() {
        verifyIllegarlArugmentException("0 10", "size should be between");
    }

    @Test
    void height_is_zero() {
        verifyIllegarlArugmentException("10 0", "size should be between");
    }

    @Test
    void width_is_negative() {
        verifyIllegarlArugmentException("-5 5", "size should be between");
    }

    @Test
    void height_is_negative() {
        verifyIllegarlArugmentException("5 -5", "size should be between");
    }

    @Test
    void canvas_is_created_with_specified_size() {
        verifySuccessfulCanvasCreation("10 20", 10, 20);
    }

    @Test
    void canvas_created_even_with_extra_spaces_in_input() {
        verifySuccessfulCanvasCreation("   20    25   ", 20, 25);
    }

    private void verifySuccessfulCanvasCreation(String commandParams, int expectedWidth, int expectedHeight) {
        sut.execute(executionContext, commandParams);
        ArgumentCaptor<Canvas> canvasArgumentCaptor = ArgumentCaptor.forClass(Canvas.class);
        verify(executionContext, times(1)).setCanvas(canvasArgumentCaptor.capture());
        assertEquals(expectedWidth, canvasArgumentCaptor.getValue().getImage().getWidth());
        assertEquals(expectedHeight, canvasArgumentCaptor.getValue().getImage().getHeight());
        verify(executionContext, times(1)).setPrintCanvasRequested(true);
    }

    private void verifyIllegarlArugmentException(String arguments, String message) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sut.execute(executionContext, arguments));
        assertTrue(exception.getMessage().contains(message));
    }
}
package com.kranvas.console.commands.impl;

import com.kranvas.console.execution.ExecutionContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class QuitCommandTest {
    private final QuitCommand sut = new QuitCommand();

    @Mock private ExecutionContext executionContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void quit_requests_system_quit() {
        sut.execute(executionContext, "");
        verify(executionContext, times(1)).requestForExit();
    }

}
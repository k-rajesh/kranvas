package com.kranvas.console.commands.impl;

import com.kranvas.console.execution.ExecutionContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class HelpCommandTest {
    private HelpCommand sut = new HelpCommand();

    @Mock private ExecutionContext executionContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void help_requests_usage_to_be_printed() {
        sut.execute(executionContext, "");
        verify(executionContext, times(1)).setPrintUsageRequested(true);
    }
}
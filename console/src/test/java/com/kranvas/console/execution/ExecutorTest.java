package com.kranvas.console.execution;

import com.kranvas.console.commands.Command;
import com.kranvas.console.rendering.CanvasConsoleRenderer;
import com.kranvas.core.Canvas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class ExecutorTest {
    @Mock
    private CommandRegistry commandRegistry;

    @Mock
    private ExecutionContext executionContext;

    @Mock
    private CanvasConsoleRenderer canvasConsoleRenderer;

    @Mock
    private Command commandA;

    private Executor sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        sut = new Executor(commandRegistry, executionContext, canvasConsoleRenderer);
    }

    @Test
    void can_handle_an_empty_registry() {
        when(commandRegistry.findCommand(any())).thenReturn(null);
        ExecutionResult result = sut.execute("some command");
        assertTrue(result.getOutput().contains("Unknown command"));
    }

    @Test
    void can_handle_unknown_command() {
        when(commandRegistry.findCommand("A")).thenReturn(commandA);
        ExecutionResult result = sut.execute("B and its arguments");
        assertTrue(result.getOutput().contains("Unknown command"));
    }

    @Test
    void can_handle_known_command() {
        when(commandRegistry.findCommand("A")).thenReturn(commandA);
        sut.execute("A and its arguments");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(commandA, times(1)).execute(any(), argumentCaptor.capture());
        assertEquals("and its arguments", argumentCaptor.getValue());
    }

    @Test
    void can_print_usage_when_requested() {
        when(commandRegistry.findCommand("A")).thenReturn(commandA);
        when(commandRegistry.getUsage()).thenReturn("Usage info");
        when(executionContext.isPrintUsageRequested()).thenReturn(true);
        ExecutionResult executionResult = sut.execute("A and its arguments");
        assertEquals("Usage info", executionResult.getOutput());
    }

    @Test
    void can_print_canvas_when_requested() {
        when(commandRegistry.findCommand("A")).thenReturn(commandA);
        when(canvasConsoleRenderer.render(any())).thenReturn("Rendered image");
        when(executionContext.isPrintCanvasRequested()).thenReturn(true);
        when(executionContext.getCanvas()).thenReturn(mock(Canvas.class));
        ExecutionResult executionResult = sut.execute("A and its arguments");
        assertEquals("Rendered image", executionResult.getOutput());
    }
}
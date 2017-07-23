package com.kranvas.console.commands.impl;

import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.core.Canvas;
import com.kranvas.core.Image;
import com.kranvas.core.Point;
import com.kranvas.tools.line.LineTool;
import com.kranvas.tools.line.LineToolParams;
import com.sun.org.apache.xpath.internal.Arg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LineCommandTest {
    private LineCommand sut = new LineCommand();
    @Mock private Canvas canvas;
    @Mock private ExecutionContext executionContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(executionContext.getCanvas()).thenReturn(canvas);
    }

    @Test
    void fewer_params_than_required() {
        verifyIllegarlArugmentException("1 2 3", "Too few");
    }

    @Test
    void too_many_params_than_required() {
        verifyIllegarlArugmentException("1 2 3 5 6", "Too many");
    }

    @Test
    void verify_that_line_tool_is_correctly_invoked() {
        sut.execute(executionContext, "1 1 1 10");
        ArgumentCaptor<LineToolParams> argumentCaptor = ArgumentCaptor.forClass(LineToolParams.class);
        verify(canvas, times(1)).applyTool(isA(LineTool.class), argumentCaptor.capture());
        assertEquals(Point.at(1, 1), argumentCaptor.getValue().getFrom());
        assertEquals(Point.at(1, 10), argumentCaptor.getValue().getTo());
        verify(executionContext, times(1)).setPrintCanvasRequested(true);
    }

    private void verifyIllegarlArugmentException(String arguments, String message) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sut.execute(executionContext, arguments));
        assertTrue(exception.getMessage().contains(message));
    }
}
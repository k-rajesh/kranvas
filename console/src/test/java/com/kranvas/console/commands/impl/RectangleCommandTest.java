package com.kranvas.console.commands.impl;

import com.kranvas.core.Canvas;
import com.kranvas.core.Point;
import com.kranvas.tools.rectange.RectangleTool;
import com.kranvas.tools.rectange.RectangleToolParams;
import com.kranvas.console.execution.ExecutionContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RectangleCommandTest {
    private final RectangleCommand sut = new RectangleCommand();
    @Mock private Canvas canvas;
    @Mock private ExecutionContext executionContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(executionContext.getCanvas()).thenReturn(canvas);
    }

    @Test
    void fewer_params_than_required() {
        verifyIllegalArugmentException("1 2 3", "Too few");
    }

    @Test
    void too_many_params_than_required() {
        verifyIllegalArugmentException("1 2 3 5 6", "Too many");
    }

    @Test
    void verify_that_rectangle_tool_is_correctly_invoked() {
        sut.execute(executionContext, "1 1 10 10");
        ArgumentCaptor<RectangleToolParams> argumentCaptor = ArgumentCaptor.forClass(RectangleToolParams.class);
        verify(canvas, times(1)).applyTool(isA(RectangleTool.class), argumentCaptor.capture());
        assertEquals(Point.at(1, 1), argumentCaptor.getValue().getTopLeft());
        assertEquals(Point.at(10, 10), argumentCaptor.getValue().getBottomRight());
        verify(executionContext, times(1)).setPrintCanvasRequested(true);
    }

    private void verifyIllegalArugmentException(String arguments, String message) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sut.execute(executionContext, arguments));
        assertTrue(exception.getMessage().contains(message));
    }
}
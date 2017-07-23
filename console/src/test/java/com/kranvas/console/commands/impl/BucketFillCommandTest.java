package com.kranvas.console.commands.impl;

import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.core.Canvas;
import com.kranvas.core.Point;
import com.kranvas.tools.bucketfill.BucketFillTool;
import com.kranvas.tools.bucketfill.BucketFillToolParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

class BucketFillCommandTest {
    private final BucketFillCommand sut = new BucketFillCommand();
    @Mock private Canvas canvas;
    @Mock private ExecutionContext executionContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(executionContext.getCanvas()).thenReturn(canvas);
    }

    @Test
    void fewer_params_than_required() {
        verifyIllegalArugmentException("1", "not specified correctly");
    }

    @Test
    void too_many_params_than_required() {
        verifyIllegalArugmentException("1 2 3 5 6 c", "Too many");
    }

    @Test
    void verify_that_rectangle_tool_is_correctly_invoked() {
        sut.execute(executionContext, "1 1 c");
        ArgumentCaptor<BucketFillToolParams> argumentCaptor = ArgumentCaptor.forClass(BucketFillToolParams.class);
        verify(canvas, times(1)).applyTool(isA(BucketFillTool.class), argumentCaptor.capture());
        assertEquals(Point.at(1, 1), argumentCaptor.getValue().getStart());
        assertEquals('c', argumentCaptor.getValue().getFillColor());
        verify(executionContext, times(1)).setPrintCanvasRequested(true);
    }

    private void verifyIllegalArugmentException(String arguments, String message) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sut.execute(executionContext, arguments));
        assertTrue(exception.getMessage().contains(message));
    }
}
package com.kranvas.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CanvasTest {

    @Test
    @SuppressWarnings("unchedked")
    void canvas_applies_tool_to_image() {
        Canvas canvas = new Canvas(5, 10, ' ');
        Tool<String> mockTool = (Tool<String>)mock(Tool.class);
        canvas.applyTool(mockTool, "Test param");
        verify(mockTool, times(1)).perform(canvas.getImage(), "Test param");
    }

    @Test
    void canvas_tool_is_null() {
        Canvas canvas = new Canvas(5, 10, ' ');
        assertThrows(IllegalArgumentException.class, () -> canvas.applyTool(null, null));
    }
}
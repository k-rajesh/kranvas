package com.kranvas.console.commands.impl;

import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.core.Point;
import com.kranvas.tools.rectange.RectangleTool;
import com.kranvas.tools.rectange.RectangleToolParams;

import java.util.List;

/**
 * Represents the Rectangle Drawing Tool
 */
public class RectangleCommand extends NumericalArgumentCommand {
    private static final String SHORT_NAME = "R";
    private static final String DESCRIPTION = "R x1 y1 x2 y2: Draws a rectangle with (x1, y1) as top left corner and (x2, y2) as bottom right corner. Points are zero-based.";
    private static final int NUM_ARGUMENTS = 4;

    private RectangleTool tool = new RectangleTool();

    public RectangleCommand() {
        super(SHORT_NAME, DESCRIPTION, NUM_ARGUMENTS);
    }

    @Override
    public void execute(ExecutionContext executionContext, List<Integer> intParams) {
        if (executionContext.getCanvas() == null)
            throw new IllegalStateException("No canvas is open, please create one");

        Point p1 = Point.at(intParams.get(0), intParams.get(1));
        Point p2 = Point.at(intParams.get(2), intParams.get(3));
        RectangleToolParams rectangleToolParams = new RectangleToolParams(p1, p2);

        executionContext.getCanvas().applyTool(tool, rectangleToolParams);
        executionContext.setPrintCanvasRequested(true);
    }
}

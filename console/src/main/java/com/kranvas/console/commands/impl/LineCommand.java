package com.kranvas.console.commands.impl;

import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.core.Point;
import com.kranvas.tools.line.LineTool;
import com.kranvas.tools.line.LineToolParams;

import java.util.List;

/**
 * Represents the Line Drawing Tool
 */
public class LineCommand extends NumericalArgumentCommand {
    private static final String SHORT_NAME = "L";
    private static final String DESCRIPTION = "L x1 y1 x2 y2: Draws either a horizontal or vertical line between (x1, y1) and (x2, y2). All points are zero-based";
    private static final int NUM_ARGUMENTS = 4;

    public LineCommand() {
        super(SHORT_NAME, DESCRIPTION, NUM_ARGUMENTS);
    }

    private final LineTool tool = new LineTool();

    @Override
    public void execute(ExecutionContext executionContext, List<Integer> intParams) {
        if (executionContext.getCanvas() == null)
            throw new IllegalStateException("No canvas is open, please create one");

        Point p1 = Point.at(intParams.get(0), intParams.get(1));
        Point p2 = Point.at(intParams.get(2), intParams.get(3));
        LineToolParams lineToolParams = new LineToolParams(p1, p2);
        executionContext.getCanvas().applyTool(tool, lineToolParams);
        executionContext.setPrintCanvasRequested(true);
    }
}

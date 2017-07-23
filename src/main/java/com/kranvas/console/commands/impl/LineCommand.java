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
    private static final String DESCRIPTION = "Draws either a horizontal or vertical line between the two given points";
    private static final int NUM_ARGUMENTS = 4;

    public LineCommand() {
        super(SHORT_NAME, DESCRIPTION, NUM_ARGUMENTS);
    }

    private LineTool tool = new LineTool();

    @Override
    public void execute(ExecutionContext executionContext, List<Integer> intParams) {
        if (executionContext.getCanvas() == null)
            throw new IllegalStateException("No canvas is open, please create one");

        Point p1 = Point.at(intParams.get(0)-1, intParams.get(1)-1);
        Point p2 = Point.at(intParams.get(2)-1, intParams.get(3)-1);
        LineToolParams lineToolParams = new LineToolParams(p1, p2);
        executionContext.getCanvas().applyTool(tool, lineToolParams);
        executionContext.setPrintCanvasRequested(true);
    }
}

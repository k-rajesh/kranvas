package com.kranvas.console.commands.impl;

import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.console.commands.Command;
import com.kranvas.console.commands.utils.IntegerListParser;
import com.kranvas.core.Point;
import com.kranvas.tools.line.LineTool;
import com.kranvas.tools.line.LineToolParams;

import java.util.List;

/**
 * Represents the Line Drawing Tool
 */
public class LineCommand implements Command {
    private static final String SHORT_NAME = "L";
    private static final String DESCRIPTION = "Draws either a horizontal or vertical line between the two given points";

    private LineTool tool = new LineTool();

    @Override
    public String getShortName() {
        return SHORT_NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void execute(ExecutionContext executionContext, String params) {
        if (executionContext.getCanvas() == null)
            throw new IllegalStateException("No canvas is open, please create one");

        LineToolParams lineToolParams = parseParams(params);
        executionContext.getCanvas().applyTool(tool, lineToolParams);
        executionContext.setPrintCanvasRequested(true);
    }

    private static LineToolParams parseParams(String params) {
        List<Integer> intParams = IntegerListParser.parse(params);
        if (intParams == null || intParams.size() < 4)
            throw new IllegalArgumentException("Line command missing mandatory arguments");

        Point p1 = Point.at(intParams.get(0), intParams.get(1));
        Point p2 = Point.at(intParams.get(2), intParams.get(3));
        return new LineToolParams(p1, p2);
    }
}

package com.kranvas.console.commands.impl;

import com.kranvas.console.commands.Command;
import com.kranvas.console.commands.utils.IntegerListParser;
import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.core.Point;
import com.kranvas.tools.rectange.RectangleTool;
import com.kranvas.tools.rectange.RectangleToolParams;

import java.util.List;

/**
 * Represents the Rectangle Drawing Tool
 */
public class RectangleCommand implements Command {
    private static final String SHORT_NAME = "R";
    private static final String DESCRIPTION = "Draws either a rectangle with the given top-left corner and the bottom-right corner";

    private RectangleTool tool = new RectangleTool();

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

        RectangleToolParams rectangleToolParams = parseParams(params);
        executionContext.getCanvas().applyTool(tool, rectangleToolParams);
        executionContext.setPrintCanvasRequested(true);
    }

    private static RectangleToolParams parseParams(String params) {
        List<Integer> intParams = IntegerListParser.parse(params);
        if (intParams == null || intParams.size() < 4)
            throw new IllegalArgumentException("Rectangle command missing mandatory arguments");

        Point p1 = Point.at(intParams.get(0), intParams.get(1));
        Point p2 = Point.at(intParams.get(2), intParams.get(3));
        return new RectangleToolParams(p1, p2);
    }
}

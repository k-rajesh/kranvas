package com.kranvas.console.commands.impl;

import com.kranvas.console.commands.Command;
import com.kranvas.console.commands.utils.IntegerListParser;
import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.core.Point;
import com.kranvas.tools.bucketfill.BucketFillTool;
import com.kranvas.tools.bucketfill.BucketFillToolParams;

import java.util.List;

/**
 * Requests the system to quit the program
 */
public class BucketFillCommand implements Command {
    private static final String SHORT_NAME = "B";
    private static final String DESCRIPTION = "Fills up all connected pixels with given color";
    private static final int NUM_ARGUMENTS = 2;

    private BucketFillTool tool = new BucketFillTool();

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
        List<Integer> intParams = IntegerListParser.parse(params);
        if (intParams == null || intParams.size() < NUM_ARGUMENTS)
            throw new IllegalArgumentException("Point not specified correctly ");

        if (intParams.size() > NUM_ARGUMENTS)
            throw new IllegalArgumentException("Too many numerical parameters, expecting only " + NUM_ARGUMENTS);

        params = params.trim();
        char fillColor = params.charAt(params.length()-1);
        if (!Character.isLetter(fillColor))
            throw new IllegalArgumentException("Fill color should be an letter");

        Point starting = Point.at(intParams.get(0), intParams.get(1));
        BucketFillToolParams bucketFillToolParams = new BucketFillToolParams(starting, fillColor);
        executionContext.getCanvas().applyTool(tool, bucketFillToolParams);
        executionContext.setPrintCanvasRequested(true);
    }
}

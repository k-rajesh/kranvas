package com.kranvas.console.commands.impl;

import com.kranvas.console.commands.utils.IntegerListParser;
import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.core.Point;
import com.kranvas.tools.bucketfill.BucketFillTool;
import com.kranvas.tools.bucketfill.BucketFillToolParams;

import java.util.List;

/**
 * Requests the system to quit the program
 */
public class BucketFillCommand extends BaseCommand {
    private static final String SHORT_NAME = "B";
    private static final String DESCRIPTION = "B x y C: Fills up (x, y) and all connected pixels with color C. Point is zero based and color must be a letter.";
    private static final int NUM_ARGUMENTS = 3;

    private final BucketFillTool tool = new BucketFillTool();

    public BucketFillCommand() {
        super(SHORT_NAME, DESCRIPTION);
    }

    @Override
    public void execute(ExecutionContext executionContext, String params) {
        if (executionContext.getCanvas() == null)
            throw new IllegalStateException("No canvas is open, please create one");

        List<Integer> intParams = IntegerListParser.parse(params);
        if (intParams == null || intParams.size() < NUM_ARGUMENTS)
            throw new IllegalArgumentException("Point not specified correctly ");

        if (intParams.size() > NUM_ARGUMENTS)
            throw new IllegalArgumentException("Too many parameters, expecting only " + NUM_ARGUMENTS);

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

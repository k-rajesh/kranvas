package com.kranvas.console.commands.impl;

import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.core.Canvas;
import com.kranvas.validations.Validation;
import com.kranvas.validations.ValidationResult;
import com.kranvas.validations.impl.NumberWithinRangeValidation;

import java.util.List;

/**
 * Creates a new instance of canvas
 */
public class CreateCanvasCommand extends NumericalArgumentCommand {
    private static final String SHORT_NAME = "C";
    private static final String DESCRIPTION = "Creates a canvas of the specified width and height";
    private static final int MAX_WIDTH = 80;
    private static final int MAX_HEIGHT = 40;
    private static final int NUM_ARGUMENTS = 2;
    private static final char BLANK_PIXEL_COLOR = ' ';

    public CreateCanvasCommand() {
        super(SHORT_NAME, DESCRIPTION, NUM_ARGUMENTS);
    }

    @Override
    public void execute(ExecutionContext executionContext, List<Integer> intParams) {
        int width = intParams.get(0);
        int height = intParams.get(1);

        ValidationResult validationResult =
                Validation.chainOfAnd(
                    NumberWithinRangeValidation.of(width, 1, MAX_WIDTH + 1),
                    NumberWithinRangeValidation.of(height, 1, MAX_HEIGHT + 1)
                ).validate();

        if (!validationResult.isValid())
            throw new IllegalArgumentException(String.format("Canvas size should be between 1x1 and %dx%d", MAX_WIDTH, MAX_HEIGHT));

        executionContext.setCanvas(new Canvas(width, height, BLANK_PIXEL_COLOR));
        executionContext.setPrintCanvasRequested(true);
    }
}

package com.kranvas.tools.rectange;

import com.kranvas.core.Image;
import com.kranvas.core.Tool;
import com.kranvas.tools.utils.RectangleDrawer;
import com.kranvas.validations.Validation;
import com.kranvas.validations.ValidationResult;
import com.kranvas.validations.impl.PointWithinImageValidation;

/**
 * Draws a rectangle between the two given points
 */
public class RectangleTool implements Tool<RectangleToolParams> {
    @Override
    public void perform(Image image, RectangleToolParams params) throws IllegalArgumentException {
        validateArguments(image, params);
        RectangleDrawer.drawRectangle(image, params.getTopLeft(), params.getBottomRight());
    }

    private static void validateArguments(Image image, RectangleToolParams params) throws IllegalArgumentException {
        if (image == null)
            throw new IllegalArgumentException("Image is null");

        if (params == null)
            throw new IllegalArgumentException("Params are null");

        ValidationResult result = Validation.chainOfAnd(
                                        PointWithinImageValidation.of(image, params.getTopLeft()),
                                        PointWithinImageValidation.of(image, params.getBottomRight())
                                  ).validate();
        if (!result.isValid())
            throw new IllegalArgumentException(result.getReason());
    }
}

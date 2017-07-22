package com.kranvas.tools.line;

import com.kranvas.image.Image;
import com.kranvas.image.Point;
import com.kranvas.tools.Tool;
import com.kranvas.validations.ValidateAllChain;
import com.kranvas.validations.ValidateAnyChain;
import com.kranvas.validations.Validation;
import com.kranvas.validations.ValidationResult;
import com.kranvas.validations.impl.HorizontalLineValidation;
import com.kranvas.validations.impl.PointWithinImageValidation;
import com.kranvas.validations.impl.VerticalLineValidation;

/**
 * Draws horizontal and vertical lines
 */
public class LineTool implements Tool<LineToolParams> {
    private static final char LINE_COLOR = 'x';

    @Override
    public void perform(Image image, LineToolParams params) throws IllegalArgumentException {
        validateArguments(image, params);

        Point from = params.getFrom();
        Point to = params.getTo();
        for(int i=from.getX(); i <= to.getX(); i++)
            for(int j=from.getY(); j <= to.getY(); j++)
                image.getPixel(Point.at(i, j)).setColor(LINE_COLOR);
    }

    private static void validateArguments(Image image, LineToolParams params) throws IllegalArgumentException {
        if (image == null)
            throw new IllegalArgumentException("Image is null");

        if (params == null)
            throw new IllegalArgumentException("Params are null");

        ValidationResult result = Validation.chainOfAnd(
                                        PointWithinImageValidation.of(image, params.getFrom()),
                                        PointWithinImageValidation.of(image, params.getTo()),
                                        Validation.chainOfAny(
                                            HorizontalLineValidation.of(params.getFrom(), params.getTo()),
                                            VerticalLineValidation.of(params.getFrom(), params.getTo())
                                        )
                                  ).validate();
        if (!result.isValid())
            throw new IllegalArgumentException(result.getReason());
    }
}

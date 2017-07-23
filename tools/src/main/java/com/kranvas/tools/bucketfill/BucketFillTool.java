package com.kranvas.tools.bucketfill;

import com.kranvas.core.Image;
import com.kranvas.core.Tool;
import com.kranvas.tools.utils.BucketFiller;
import com.kranvas.validations.ValidationResult;
import com.kranvas.validations.impl.PointWithinImageValidation;

/**
 * Fills out empty pixels with a fill color
 */
public class BucketFillTool implements Tool<BucketFillToolParams> {
    private static final char BLANK_PIXEL_COLOR = ' ';

    @Override
    public void perform(Image image, BucketFillToolParams params) throws IllegalArgumentException {
        validateArguments(image, params);
        new BucketFiller(image, params.getStart(), BLANK_PIXEL_COLOR, params.getFillColor()).fill();
    }

    private static void validateArguments(Image image, BucketFillToolParams params) throws IllegalArgumentException {
        if (image == null)
            throw new IllegalArgumentException("Image is null");

        if (params == null)
            throw new IllegalArgumentException("Params are null");

        ValidationResult result = PointWithinImageValidation.of(image, params.getStart()).validate();
        if (!result.isValid())
            throw new IllegalArgumentException(result.getReason());
    }
}

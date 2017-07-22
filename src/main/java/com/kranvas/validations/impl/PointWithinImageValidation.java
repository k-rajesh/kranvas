package com.kranvas.validations.impl;

import com.kranvas.image.Image;
import com.kranvas.image.Point;
import com.kranvas.validations.Validation;
import com.kranvas.validations.ValidationResult;

/**
 * Verifies that the given point is within the given image
 */
public class PointWithinImageValidation extends Validation {
    private Image image;
    private Point point;

    private PointWithinImageValidation(Image image, Point point) {
        this.image = image;
        this.point = point;
    }

    public static PointWithinImageValidation of(Image image, Point point) {
        return new PointWithinImageValidation(image, point);
    }

    @Override
    public ValidationResult validate() {
        if (image == null)
            return ValidationResult.failure("Image is null");

        if (point == null)
            return ValidationResult.failure("Point is null");

        return Validation.chainOfAnd(
                    NumberWithinRangeValidation.of(point.getX(), 0, image.getWidth()),
                    NumberWithinRangeValidation.of(point.getY(), 0, image.getHeight()))
                .validate();
    }
}

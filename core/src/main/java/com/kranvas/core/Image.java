package com.kranvas.core;

import com.kranvas.validations.Validation;
import com.kranvas.validations.ValidationResult;
import com.kranvas.validations.impl.NonNegativeNumberValidation;

/**
 * A zero-based representation of a 2D rectangular image
 * Top Left corner is (0,0) and Bottom Right corner is (width-1, height-1)
 */
public abstract class Image {
    private final int width;
    private final int height;

    protected Image(int width, int height) {
        this.width = width;
        this.height = height;
        validateWidthAndHeight();
    }

    /**
     * Width of the image as number of pixels along the X axis
     * @return a positive integer presenting the width of the image
     */
    public int getWidth() {
        return width;
    }

    /**
     * Height of the image as number of pixels along the Y axis
     * @return a positive integer presenting the height of the image
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retrieves the pixel based on the 0-based 2D location in the image
     * @param point location of the pixel in the 2D plane
     * @return the pixel located at the given co-ordinates
     * @throws IllegalArgumentException if the given co-ordinates are out of bounds of the image
     */
    public abstract Pixel getPixel(Point point) throws IllegalArgumentException;

    private void validateWidthAndHeight() throws IllegalArgumentException {
        ValidationResult result = Validation.chainOfAnd(
                                        NonNegativeNumberValidation.of(getWidth()),
                                        NonNegativeNumberValidation.of(getHeight()))
                                  .validate();
        if (!result.isValid())
            throw new IllegalArgumentException(result.getReason());
    }
}

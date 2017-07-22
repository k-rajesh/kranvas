package com.kranvas.image.impl;

import com.kranvas.image.Image;
import com.kranvas.image.Pixel;
import com.kranvas.image.Point;
import com.kranvas.validations.Validation;
import com.kranvas.validations.ValidationResult;
import com.kranvas.validations.impl.NonNegativeNumberValidation;
import com.kranvas.validations.impl.NumberWithinRangeValidation;

class BitmapImage implements Image {
    private int width;
    private int height;
    private Pixel[][] pixels;

    BitmapImage(int width, int height) {
        this.width = width;
        this.height = height;
        initPixels();
    }

    private void initPixels() {
        validateWidthAndHeight();
        this.pixels = new Pixel[width][height];
        for(int i=0; i < width; i++)
            for(int j=0; j < height; j++)
                pixels[i][j] = new Pixel();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pixel getPixel(Point point) throws IllegalArgumentException {
        validateCoordinates(point);
        return pixels[point.getX()][point.getX()];
    }

    private void validateWidthAndHeight() throws IllegalArgumentException {
        ValidationResult result = Validation.chainOfAnd(
                                    NonNegativeNumberValidation.of(width),
                                    NonNegativeNumberValidation.of(height))
                                  .validate();
        if (!result.isValid())
            throw new IllegalArgumentException(result.getReason());
    }

    private void validateCoordinates(Point point) throws IllegalArgumentException {
        ValidationResult result = Validation.chainOfAnd(
                                    NumberWithinRangeValidation.of(point.getX(), 0, width),
                                    NumberWithinRangeValidation.of(point.getY(), 0, height))
                                  .validate();
        if (!result.isValid())
            throw new IllegalArgumentException(result.getReason());
    }
}

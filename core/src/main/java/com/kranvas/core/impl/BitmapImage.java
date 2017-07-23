package com.kranvas.core.impl;

import com.kranvas.core.Image;
import com.kranvas.core.Pixel;
import com.kranvas.core.Point;
import com.kranvas.validations.Validation;
import com.kranvas.validations.ValidationResult;
import com.kranvas.validations.impl.NumberWithinRangeValidation;

public class BitmapImage extends Image {
    private final char defaultColor;
    private Pixel[][] pixels;

    public BitmapImage(int width, int height, char defaultColor) {
        super(width, height);
        this.defaultColor = defaultColor;
        initPixels();
    }

    private void initPixels() {
        this.pixels = new Pixel[getHeight()][getWidth()];
        for(int i=0; i < getHeight(); i++)
            for(int j=0; j < getWidth(); j++)
                pixels[i][j] = new Pixel(defaultColor);
    }

    @Override
    public Pixel getPixel(Point point) throws IllegalArgumentException {
        validateCoordinates(point);
        return pixels[point.getY()][point.getX()];
    }

    private void validateCoordinates(Point point) throws IllegalArgumentException {
        ValidationResult result = Validation.chainOfAnd(
                                    NumberWithinRangeValidation.of(point.getX(), 0, getWidth()),
                                    NumberWithinRangeValidation.of(point.getY(), 0, getHeight()))
                                  .validate();
        if (!result.isValid())
            throw new IllegalArgumentException(result.getReason());
    }
}

package com.kranvas.image.impl;

import com.kranvas.image.Image;
import com.kranvas.image.Pixel;
import com.kranvas.image.Point;
import com.kranvas.validations.Validation;
import com.kranvas.validations.ValidationResult;
import com.kranvas.validations.impl.NonNegativeNumberValidation;
import com.kranvas.validations.impl.NumberWithinRangeValidation;

public class BitmapImage extends Image {
    private Pixel[][] pixels;

    public BitmapImage(int width, int height) {
        super(width, height);
        initPixels();
    }

    private void initPixels() {
        this.pixels = new Pixel[getWidth()][getHeight()];
        for(int i=0; i < getWidth(); i++)
            for(int j=0; j < getHeight(); j++)
                pixels[i][j] = new Pixel();
    }

    @Override
    public Pixel getPixel(Point point) throws IllegalArgumentException {
        validateCoordinates(point);
        return pixels[point.getX()][point.getX()];
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

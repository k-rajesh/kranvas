package com.kranvas.image;

/**
 * A zero-based representation of a 2D rectangular image
 * Top Left corner is (0,0) and Bottom Right corner is (width-1, height-1)
 */
public interface Image {
    /**
     * Width of the image as number of pixels along the X axis
     * @return a positive integer presenting the width of the image
     */
    int getWidth();

    /**
     * Height of the image as number of pixels along the Y axis
     * @return a positive integer presenting the height of the image
     */
    int getHeight();

    /**
     * Retrieves the pixel based on the 0-based 2D location in the image
     * @param point location of the pixel in the 2D plane
     * @return the pixel located at the given co-ordinates
     * @throws IllegalArgumentException if the given co-ordinates are out of bounds of the image
     */
    Pixel getPixel(Point point) throws IllegalArgumentException;
}

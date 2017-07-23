package com.kranvas.core;

/**
 * Represents one pixel in the image with a single attribute, the color of the pixel
 */
public class Pixel {
    private char color;

    public Pixel(char color) {
        this.color = color;
    }

    /**
     * The color of the pixel
     * @return a character code representation of the color of the pixel
     */
    public char getColor() {
        return color;
    }

    /**
     * Changes the color of the pixel to the given value
     * @param color the new color of the pixel
     */
    public void setColor(char color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Pixel)) {
            return false;
        }
        Pixel user = (Pixel) o;
        return color == user.color;
    }

    @Override
    public int hashCode() {
        return color;
    }
}

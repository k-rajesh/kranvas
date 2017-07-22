package com.kranvas.image;

import com.kranvas.validations.Validation;
import com.kranvas.validations.ValidationResult;
import com.kranvas.validations.impl.NonNegativeNumberValidation;
import com.kranvas.validations.ValidateAllChain;

/**
 * Represents a coordinate on a 2D plane
 */
public class Point {
    private int x, y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a point with given coordinates
     * @param x the 0-based x co-ordinate
     * @param y the 0-based y co-ordinate
     */
    public static Point at(int x, int y) throws IllegalArgumentException {
        validateCoordinates(x, y);
        return new Point(x, y);
    }

    /**
     * Gets the X co-ordinate of this point
     * @return an integer representing the 0-based X-coordinate of this point
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the Y co-ordinate of this point
     * @return an integer representing the 0-based Y-coordinate of this point
     */
    public int getY() {
        return y;
    }

    private static void validateCoordinates(int x, int y) throws IllegalArgumentException {
        ValidationResult result = Validation.chainOfAnd(
                                    NonNegativeNumberValidation.of(x),
                                    NonNegativeNumberValidation.of(y))
                                  .validate();
        if (!result.isValid())
            throw new IllegalArgumentException(String.format("Co-ordinates must be non-negative. The given co-ordinates (%d, %d) are not valid", x, y));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;
        return getX() == point.getX() && getY() == point.getY();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}

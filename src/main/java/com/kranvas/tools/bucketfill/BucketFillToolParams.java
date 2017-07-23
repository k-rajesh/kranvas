package com.kranvas.tools.bucketfill;

import com.kranvas.core.Point;

/**
 * The parameters to perform bucket fill
 */
public class BucketFillToolParams {
    private final char fillColor;
    private Point start;

    public BucketFillToolParams(Point start, Character fillColor) {
        if (start == null)
            throw new IllegalArgumentException("Point cannot be null");

        if (fillColor == null)
            throw new IllegalArgumentException("Fill color cannot be null");

        this.start = start;
        this.fillColor = fillColor;
    }

    public Point getStart() {
        return start;
    }

    public char getFillColor() { return fillColor; }
}

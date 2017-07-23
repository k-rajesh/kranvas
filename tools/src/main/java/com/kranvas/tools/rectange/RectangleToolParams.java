package com.kranvas.tools.rectange;

import com.kranvas.core.Point;

/**
 * The parameters to draw a rectangle
 */
public class RectangleToolParams {
    private Point topLeft, bottomRight;

    public RectangleToolParams(Point p1, Point p2) {
        if (p1 == null || p2 == null)
            throw new IllegalArgumentException("Points cannot be null");

        if (p1.compareTo(p2) <= 0) {
            this.topLeft = p1;
            this.bottomRight = p2;
        } else {
            this.topLeft = p2;
            this.bottomRight = p1;
        }
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }
}

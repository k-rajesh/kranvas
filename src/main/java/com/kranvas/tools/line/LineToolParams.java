package com.kranvas.tools.line;

import com.kranvas.image.Point;

/**
 * The parameters to draw a line
 */
public class LineToolParams {
    private Point from, to;

    public LineToolParams(Point p1, Point p2) {
        if (p1 == null || p2 == null)
            throw new IllegalArgumentException("Both points cannot be null");

        if (p1.getY() + p1.getX() <= p2.getY() + p2.getX()) {
            this.from = p1;
            this.to = p2;
        } else {
            this.from = p2;
            this.to = p1;
        }
    }

    Point getFrom() {
        return from;
    }

    Point getTo() {
        return to;
    }
}

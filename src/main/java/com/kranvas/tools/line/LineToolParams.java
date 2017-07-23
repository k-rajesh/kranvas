package com.kranvas.tools.line;

import com.kranvas.core.Point;

/**
 * The parameters to draw a line
 */
public class LineToolParams {
    private Point from, to;

    public LineToolParams(Point p1, Point p2) {
        if (p1 == null || p2 == null)
            throw new IllegalArgumentException("Points cannot be null");

        if (p1.compareTo(p2) <= 0) {
            this.from = p1;
            this.to = p2;
        } else {
            this.from = p2;
            this.to = p1;
        }
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }
}

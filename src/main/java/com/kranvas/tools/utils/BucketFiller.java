package com.kranvas.tools.utils;

import com.kranvas.core.Image;
import com.kranvas.core.Point;

import java.util.Stack;

public class BucketFiller {
    private final Image image;
    private final Point start;
    private final char filColor;
    private boolean[][] visisted;
    private Stack<Point> toVisit;
    private char targetColor;

    public BucketFiller(Image image, Point start, char targetColor, char filColor) {
        this.targetColor = targetColor;
        if (image == null)
            throw new IllegalArgumentException("Image cannot be null");

        if (start == null)
            throw new IllegalArgumentException("Start point cannot be null");

        this.image = image;
        this.start = start;
        this.filColor = filColor;
        this.targetColor = targetColor;
    }

    public void fill() {
        visisted = new boolean[image.getWidth()][image.getHeight()];
        toVisit = new Stack<>();
        scheduleIfSafe(start);

        while(!toVisit.empty()) {
            Point point = toVisit.pop();
            image.getPixel(point).setColor(filColor);

            if (point.getX() > 0) {
                Point left = Point.at(point.getX()-1, point.getY());
                scheduleIfSafe(left);
            }

            if (point.getY() > 0) {
                Point top = Point.at(point.getX(), point.getY()-1);
                scheduleIfSafe(top);
            }

            Point right = Point.at(point.getX()+1, point.getY());
            scheduleIfSafe(right);

            Point bottom = Point.at(point.getX(), point.getY()+1);
            scheduleIfSafe(bottom);
        }
    }

    private void scheduleIfSafe(Point point) {
        if (isSafe(point))
            schedulePoint(point);
    }

    private boolean isSafe(Point point) {
        return
            point.getX() < image.getWidth() &&
            point.getY() < image.getHeight() &&
            !visisted[point.getX()][point.getY()] &&
            targetColor == image.getPixel(point).getColor();
    }

    private void schedulePoint(Point point) {
        toVisit.push(point);
        visisted[point.getX()][point.getY()] = true;
    }
}

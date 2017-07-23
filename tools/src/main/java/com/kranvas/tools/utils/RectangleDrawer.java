package com.kranvas.tools.utils;

import com.kranvas.core.Image;
import com.kranvas.core.Point;

public class RectangleDrawer {
    public static void drawRectangle(Image image, Point topLeft, Point bottomRight) {
        // Figure out the remaining two corners
        Point topRight = Point.at(bottomRight.getX(), topLeft.getY());
        Point bottomLeft = Point.at(topLeft.getX(), bottomRight.getY());

        // Calculate the width and height
        int width = bottomRight.getX() - topLeft.getX() + 1;
        int height = bottomRight.getY() - topLeft.getY() + 1;

        // Draw the four lines now
        LineDrawer.drawHorizontalLine(image, topLeft, width);

        if (height > 1)
            LineDrawer.drawHorizontalLine(image, bottomLeft, width);

        if (height > 2) {
            LineDrawer.drawVerticalLine(image, Point.at(topLeft.getX(), topLeft.getY()+1), height - 2);
            LineDrawer.drawVerticalLine(image, Point.at(topRight.getX(), topRight.getY()+1), height - 2);
        }
    }
}

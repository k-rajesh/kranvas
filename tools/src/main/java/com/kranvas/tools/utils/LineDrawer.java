package com.kranvas.tools.utils;

import com.kranvas.core.Image;
import com.kranvas.core.Point;

public class LineDrawer {
    private static final char LINE_COLOR = 'x';

    public static void drawHorizontalLine(Image image, Point start, int width) {
        for(int i=0; i < width; i++)
            image.getPixel(Point.at(start.getX()+i, start.getY())).setColor(LINE_COLOR);
    }

    public static void drawVerticalLine(Image image, Point start, int height) {
        for(int i=0; i < height; i++)
            image.getPixel(Point.at(start.getX(), start.getY()+i)).setColor(LINE_COLOR);
    }
}

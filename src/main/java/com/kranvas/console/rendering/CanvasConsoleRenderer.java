package com.kranvas.console.rendering;

import com.kranvas.core.Canvas;
import com.kranvas.core.Image;
import com.kranvas.core.Pixel;
import com.kranvas.core.Point;

/**
 * Renders an image as a string to print on the console
 */
public class CanvasConsoleRenderer {
    private static final char HORIZONTAL_BORDER_CHAR = '-';
    private static final char VERTICAL_BORDER_CHAR = '|';
    private static final char BLANK_PIXEL_CHAR = ' ';

    public String render(Canvas canvas) {
        if (canvas == null)
            return null;

        Image image = canvas.getImage();
        StringBuilder sb = new StringBuilder();
        printHorizontalBorder(image, sb);
        for(int v=0; v < image.getHeight(); v++) {
            sb.append(VERTICAL_BORDER_CHAR);
            for(int h=0; h < image.getWidth(); h++) {
                sb.append(getPixelAsChar(image, h, v));
            }
            sb.append(VERTICAL_BORDER_CHAR);
            sb.append(System.lineSeparator());
        }
        printHorizontalBorder(image, sb);
        return sb.toString();
    }

    private char getPixelAsChar(Image image, int x, int y) {
        Pixel pixel = image.getPixel(Point.at(x, y));
        return pixel == null ? BLANK_PIXEL_CHAR : pixel.getColor();
    }

    private void printHorizontalBorder(Image image, StringBuilder sb) {
        String horizontalLine = repeatChar(HORIZONTAL_BORDER_CHAR, image.getWidth() + 2);
        sb.append(horizontalLine).append(System.lineSeparator());
    }

    private static String repeatChar(char c, int repeatCount) {
        return new String(new char[repeatCount]).replace('\0', c);
    }
}

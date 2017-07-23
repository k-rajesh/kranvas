package com.kranvas.core;

import com.kranvas.core.impl.BitmapImage;
import com.kranvas.tools.Tool;

public class Canvas {
    private static final char BLANK_PIXEL_COLOR = ' ';
    private Image image;

    public Canvas(int width, int height) {
        image = new BitmapImage(width, height, BLANK_PIXEL_COLOR);
    }

    public Image getImage() {
        return image;
    }

    public <T> void applyTool (Tool<T> tool, T toolParams) {
        if (tool == null)
            throw new IllegalArgumentException("Tool is null");

        tool.perform(image, toolParams);
    }
}

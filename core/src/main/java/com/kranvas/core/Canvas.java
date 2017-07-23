package com.kranvas.core;

import com.kranvas.core.impl.BitmapImage;

public class Canvas {
    private Image image;

    public Canvas(int width, int height, char blankCellColor) {
        image = new BitmapImage(width, height, blankCellColor);
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

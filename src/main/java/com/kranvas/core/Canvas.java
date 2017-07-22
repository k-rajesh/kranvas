package com.kranvas.core;

import com.kranvas.image.Image;
import com.kranvas.image.impl.BitmapImage;
import com.kranvas.tools.Tool;

public class Canvas {
    private Image image;

    public Canvas(int width, int height) {
        image = new BitmapImage(width, height);
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

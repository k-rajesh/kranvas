package com.kranvas.tools;

import com.kranvas.image.Image;

/**
 * A tool manipulates a given image
 */
public interface Tool<T> {
    /**
     * Performs an operation on the given image as defined by the parameters
     * @param image the image to be manipulated
     * @param params the parameters to perform the change
     */
    void perform(Image image, T params) throws IllegalArgumentException;
}

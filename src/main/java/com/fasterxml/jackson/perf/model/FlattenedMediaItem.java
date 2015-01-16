package com.fasterxml.jackson.perf.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Separate root value class used with data formats like CSV that can not
 * deal well with arrays of POJOs, or with nested POJOs.
 * Because of this, all values are effectively unwrapped, and only two
 * "Photo" values can be included.
 */
public class FlattenedMediaItem
    extends MediaContent
{
    @JsonUnwrapped(prefix="image1")
    protected Image image1;

    @JsonUnwrapped(prefix="image2")
    protected Image image2;

    public FlattenedMediaItem() { }

    public FlattenedMediaItem(MediaContent base, Image p1, Image p2) {
        super(base);
        image1 = p1;
        image2 = p2;
    }

    public Image getImage1() { return image1; }
    public Image getImage2() { return image2; }
}

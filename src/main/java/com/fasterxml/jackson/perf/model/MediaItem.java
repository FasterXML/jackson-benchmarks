package com.fasterxml.jackson.perf.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"content", "images"})
public class MediaItem
{
    private MediaContent _content;
    private List<Image> _images;

    public MediaItem() { }

    public MediaItem(MediaContent c) {
        _content = c;
    }

    public void addPhoto(Image p) {
        if (_images == null) {
            _images = new ArrayList<Image>();
        }
        _images.add(p);
    }
    
    public List<Image> getImages() { return _images; }
    public void setImages(List<Image> p) { _images = p; }

    public MediaContent getContent() { return _content; }
    public void setContent(MediaContent c) { _content = c; }
}

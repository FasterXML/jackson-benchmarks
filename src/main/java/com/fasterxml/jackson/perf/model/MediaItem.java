package com.fasterxml.jackson.perf.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"content", "images"})
public class MediaItem
{
    private List<Photo> _photos;
    private MediaContent _content;

    public MediaItem() { }

    public MediaItem(MediaContent c)
    {
        _content = c;
    }

    public void addPhoto(Photo p) {
        if (_photos == null) {
            _photos = new ArrayList<Photo>();
        }
        _photos.add(p);
    }
    
    public List<Photo> getImages() { return _photos; }
    public void setImages(List<Photo> p) { _photos = p; }

    public MediaContent getContent() { return _content; }
    public void setContent(MediaContent c) { _content = c; }
}

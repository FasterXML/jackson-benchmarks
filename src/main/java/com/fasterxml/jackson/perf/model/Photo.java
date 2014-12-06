package com.fasterxml.jackson.perf.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"uri","title","width","height","size"})
public class Photo
{
    private String _uri;
    private String _title;
    private int _width;
    private int _height;
    private Size _size;

    public Photo() {}
    public Photo(String uri, String title, int w, int h, Size s)
    {
      _uri = uri;
      _title = title;
      _width = w;
      _height = h;
      _size = s;
    }

  public String getUri() { return _uri; }
  public String getTitle() { return _title; }
  public int getWidth() { return _width; }
  public int getHeight() { return _height; }
  public Size getSize() { return _size; }

  public void setUri(String u) { _uri = u; }
  public void setTitle(String t) { _title = t; }
  public void setWidth(int w) { _width = w; }
  public void setHeight(int h) { _height = h; }
  public void setSize(Size s) { _size = s; }
}
package com.fasterxml.jackson.perf.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(alphabetic=true, value = {
      "player","uri","title","width","height","format","duration","size","bitrate","copyright","persons"})
public class MediaContent
{
    public enum Player {
        JAVA, FLASH;

        public static Player find(String str) {
            if (str == "JAVA") return JAVA;
            if (str == "FLASH") return FLASH;
            if (str == null) return null;
            if ("JAVA".equals(str)) return JAVA;
            if ("FLASH".equals(str)) return FLASH;
            String desc = String.format("'%s'", str);
            throw new IllegalArgumentException("No Player value of "+desc);
        }
    }

    private Player _player;
    private String _uri;
    private String _title;
    private int _width;
    private int _height;
    private String _format;
    private long _duration;
    private long _size;
    private int _bitrate;
    private List<String> _persons;
    private String _copyright;

    public MediaContent() { }

    protected MediaContent(MediaContent src) {
        _player = src._player;
        _uri = src._uri;
        _title = src._title;
        _width = src._width;
        _height = src._height;
        _format = src._format;
        _duration = src._duration;
        _size = src._size;
        _bitrate = src._bitrate;
        _persons = src._persons;
        _copyright = src._copyright;
    }

    public void addPerson(String p) {
        if (_persons == null) {
            _persons = new ArrayList<String>();
        }
        _persons.add(p);
    }

    public Player getPlayer() { return _player; }
    public String getUri() { return _uri; }
    public String getTitle() { return _title; }
    public int getWidth() { return _width; }
    public int getHeight() { return _height; }
    public String getFormat() { return _format; }
    public long getDuration() { return _duration; }
    public long getSize() { return _size; }
    public int getBitrate() { return _bitrate; }
    public List<String> getPersons() { return _persons; }
    public String getCopyright() { return _copyright; }

    public void setPlayer(Player p) { _player = p; }
    public void setUri(String u) {  _uri = u; }
    public void setTitle(String t) {  _title = t; }
    public void setWidth(int w) {  _width = w; }
    public void setHeight(int h) {  _height = h; }
    public void setFormat(String f) {  _format = f;  }
    public void setDuration(long d) {  _duration = d; }
    public void setSize(long s) {  _size = s; }
    public void setBitrate(int b) {  _bitrate = b; }
    public void setPersons(List<String> p) {  _persons = p; }
    public void setCopyright(String c) {  _copyright = c; }
}
package com.fasterxml.jackson.perf.model;

import com.fasterxml.jackson.perf.model.MediaItem.Player;

public class MediaItems
{
    // from good old jvm-serializers
    protected final static MediaItem STD_MEDIA_ITEM;
    static {
        MediaItem.Content content = new MediaItem.Content();
        content.setUri("http://javaone.com/keynote.mpg");
        content.setTitle("Javaone Keynote");
        content.setWidth(640);
        content.setHeight(480);
        content.setFormat("video/mpg4");
        content.setDuration(18000000);
        content.setSize(58982400L);
        content.setBitrate(262144);
        content.setPlayer(Player.JAVA);
        content.setCopyright("None");
        content.addPerson("Bill Gates");
        content.addPerson("Steve Jobs");

        MediaItem item = new MediaItem(content);
        item.addPhoto(new MediaItem.Photo("http://javaone.com/keynote_large.jpg", "Javaone Keynote",
                1024, 768, MediaItem.Size.LARGE));
        item.addPhoto(new MediaItem.Photo("http://javaone.com/keynote_small.jpg", "Javaone Keynote",
                320, 240, MediaItem.Size.SMALL));

        STD_MEDIA_ITEM = item;
    }
    
    public static MediaItem stdMediaItem() {
    	return STD_MEDIA_ITEM;
    }
}

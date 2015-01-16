package com.fasterxml.jackson.perf.model;

public class MediaItems
{
    protected final static Image IMAGE1 = new Image("http://javaone.com/keynote_large.jpg",
            "Javaone Keynote", 1024, 768, Size.LARGE);
    protected final static Image IMAGE2 = new Image("http://javaone.com/keynote_small.jpg",
            "Javaone Keynote", 320, 240, Size.SMALL);

    // from good old jvm-serializers
    protected final static MediaItem STD_MEDIA_ITEM;
    static {
        MediaContent content = new MediaContent();
        content.setUri("http://javaone.com/keynote.mpg");
        content.setTitle("Javaone Keynote");
        content.setWidth(640);
        content.setHeight(480);
        content.setFormat("video/mpg4");
        content.setDuration(18000000);
        content.setSize(58982400L);
        content.setBitrate(262144);
        content.setPlayer(MediaContent.Player.JAVA);
        content.setCopyright("None");
        content.addPerson("Bill Gates");
        content.addPerson("Steve Jobs");

        MediaItem item = new MediaItem(content);
        item.addPhoto(IMAGE1);
        item.addPhoto(IMAGE2);

        STD_MEDIA_ITEM = item;
    }

    protected final static FlattenedMediaItem FLAT_MEDIA_ITEM =
            new FlattenedMediaItem(STD_MEDIA_ITEM.getContent(), IMAGE1, IMAGE2);

    public static MediaItem stdMediaItem() {
        return STD_MEDIA_ITEM;
    }

    public static FlattenedMediaItem flatMediaItem() {
        return FLAT_MEDIA_ITEM;
    }
}

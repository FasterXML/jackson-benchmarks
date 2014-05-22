package com.fasterxml.jackson.perf.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumMap;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItem.Player;

public class InputConverter
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
    
    protected final EnumMap<InputData, byte[]> _data;

    protected final byte[] _mediaItemBytes;
    
    public InputConverter(ObjectMapper targetMapper)
    {
        EnumMap<InputData, byte[]> data = new EnumMap<>(InputData.class);

        try {
            // special case, mostly just to support non-converted JSON
            if (targetMapper == null) {
                for (InputData input : InputData.values()) {
                    data.put(input, input.bytes());
                }
            } else {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream(4000);
                final JsonFactory jsonF = new JsonFactory();
                final JsonFactory targetF = targetMapper.getFactory();
        
                for (InputData input : InputData.values()) {
                    JsonParser in = jsonF.createParser(input.bytes());
                    bytes.reset();
                    JsonGenerator out = targetF.createGenerator(bytes);
                    while (in.nextToken() != null) {
                        out.copyCurrentStructure(in);
                    }
                    in.close();
                    out.close();
                    data.put(input, bytes.toByteArray());
                }
            }

            // but can't avoid serializing typed values
            _mediaItemBytes = targetMapper.writeValueAsBytes(STD_MEDIA_ITEM);
        
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        _data = data;
    }

    public byte[] bytes(InputData type) {
        return _data.get(type);
    }

    public byte[] bytesForMediaItem() {
        return _mediaItemBytes;
    }
}

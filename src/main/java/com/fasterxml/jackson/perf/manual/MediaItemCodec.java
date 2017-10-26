package com.fasterxml.jackson.perf.manual;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.perf.model.Image;
import com.fasterxml.jackson.perf.model.MediaContent;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.Size;

public class MediaItemCodec
{
    protected final static int FIELD_IX_CONTENT = 1;
    protected final static String FIELD_NAME_CONTENT = "content";
    protected final static int FIELD_IX_IMAGES = 2;
    protected final static String FIELD_NAME_IMAGES = "images";
    protected final static int FIELD_IX_PLAYER = 3;
    protected final static String FIELD_NAME_PLAYER = "player";
    protected final static int FIELD_IX_URI = 4;
    protected final static String FIELD_NAME_URI = "uri";
    protected final static int FIELD_IX_TITLE = 5;
    protected final static String FIELD_NAME_TITLE = "title";
    protected final static int FIELD_IX_WIDTH = 6;
    protected final static String FIELD_NAME_WIDTH = "width";
    protected final static int FIELD_IX_HEIGHT = 7;
    protected final static String FIELD_NAME_HEIGHT = "height";
    protected final static int FIELD_IX_FORMAT = 8;
    protected final static String FIELD_NAME_FORMAT = "format";
    protected final static int FIELD_IX_DURATION = 9;
    protected final static String FIELD_NAME_DURATION = "duration";
    protected final static int FIELD_IX_SIZE = 10;
    protected final static String FIELD_NAME_SIZE = "size";
    protected final static int FIELD_IX_BITRATE = 11;
    protected final static String FIELD_NAME_BITRATE = "bitrate";
    protected final static int FIELD_IX_PERSONS = 12;
    protected final static String FIELD_NAME_PERSONS = "persons";
    protected final static int FIELD_IX_COPYRIGHT = 13;
    protected final static String FIELD_NAME_COPYRIGHT = "copyright";
    
    protected final static SerializedString FIELD_IMAGES = new SerializedString(FIELD_NAME_IMAGES);

    protected final static SerializedString FIELD_CONTENT = new SerializedString(FIELD_NAME_CONTENT);
    protected final static SerializedString FIELD_PLAYER = new SerializedString(FIELD_NAME_PLAYER);

    protected final static SerializedString FIELD_URI = new SerializedString(FIELD_NAME_URI);
    protected final static SerializedString FIELD_TITLE = new SerializedString(FIELD_NAME_TITLE);
    protected final static SerializedString FIELD_WIDTH = new SerializedString(FIELD_NAME_WIDTH);
    protected final static SerializedString FIELD_HEIGHT = new SerializedString(FIELD_NAME_HEIGHT);
    protected final static SerializedString FIELD_FORMAT = new SerializedString(FIELD_NAME_FORMAT);
    protected final static SerializedString FIELD_DURATION = new SerializedString(FIELD_NAME_DURATION);
    protected final static SerializedString FIELD_SIZE = new SerializedString(FIELD_NAME_SIZE);
    protected final static SerializedString FIELD_BITRATE = new SerializedString(FIELD_NAME_BITRATE);
    protected final static SerializedString FIELD_COPYRIGHT = new SerializedString(FIELD_NAME_COPYRIGHT);
    protected final static SerializedString FIELD_PERSONS = new SerializedString(FIELD_NAME_PERSONS);

    public static final HashMap<String,Integer> fieldToIndex = new HashMap<String,Integer>();
    static {
        fieldToIndex.put(FIELD_NAME_CONTENT, FIELD_IX_CONTENT);
        fieldToIndex.put(FIELD_NAME_IMAGES, FIELD_IX_IMAGES);
        fieldToIndex.put(FIELD_NAME_PLAYER, FIELD_IX_PLAYER);
        fieldToIndex.put(FIELD_NAME_URI, FIELD_IX_URI);
        fieldToIndex.put(FIELD_NAME_TITLE, FIELD_IX_TITLE);
        fieldToIndex.put(FIELD_NAME_WIDTH, FIELD_IX_WIDTH);
        fieldToIndex.put(FIELD_NAME_HEIGHT, FIELD_IX_HEIGHT);
        fieldToIndex.put(FIELD_NAME_FORMAT, FIELD_IX_FORMAT);
        fieldToIndex.put(FIELD_NAME_DURATION, FIELD_IX_DURATION);
        fieldToIndex.put(FIELD_NAME_SIZE, FIELD_IX_SIZE);
        fieldToIndex.put(FIELD_NAME_BITRATE, FIELD_IX_BITRATE);
        fieldToIndex.put(FIELD_NAME_PERSONS, FIELD_IX_PERSONS);
        fieldToIndex.put(FIELD_NAME_COPYRIGHT, FIELD_IX_COPYRIGHT);
    }

    //////////////////////////////////////////////////
    // Serialization
    //////////////////////////////////////////////////
         
    public void serialize(JsonGenerator generator, MediaItem item) throws IOException
    {
        generator.writeStartObject();
        generator.writeFieldName(FIELD_CONTENT);
        writeContent(generator, item.getContent());
        generator.writeFieldName(FIELD_IMAGES);
        generator.writeStartArray();
        for (Image i : item.getImages()) {
            writeImage(generator, i);
        }
        generator.writeEndArray();
        generator.writeEndObject();
    }

    private void writeContent(JsonGenerator generator, MediaContent media) throws IOException
    {
        generator.writeStartObject();
        generator.writeFieldName(FIELD_PLAYER);
        generator.writeString(media.getPlayer().name());
        generator.writeFieldName(FIELD_URI);
        generator.writeString(media.getUri());
        String title = media.getTitle();
        if (title != null) {
            generator.writeFieldName(FIELD_TITLE);
            generator.writeString(title);
        }
        generator.writeFieldName(FIELD_WIDTH);
        generator.writeNumber(media.getWidth());
        generator.writeFieldName(FIELD_HEIGHT);
        generator.writeNumber(media.getHeight());
        generator.writeFieldName(FIELD_FORMAT);
        generator.writeString(media.getFormat());
        generator.writeFieldName(FIELD_DURATION);
        generator.writeNumber(media.getDuration());
        generator.writeFieldName(FIELD_SIZE);
        generator.writeNumber(media.getSize());
        
        generator.writeFieldName(FIELD_BITRATE);
        generator.writeNumber(media.getBitrate());

        String copyright = media.getCopyright();
        if (copyright != null) {
            generator.writeFieldName(FIELD_COPYRIGHT);
            generator.writeString(copyright);
        }
        generator.writeFieldName(FIELD_PERSONS);
        generator.writeStartArray();
        for (String person : media.getPersons()) {
            generator.writeString(person);
        }
        generator.writeEndArray();
        generator.writeEndObject();
    }

    private void writeImage(JsonGenerator generator, Image image) throws IOException
    {
        generator.writeStartObject();
        generator.writeFieldName(FIELD_URI);
        generator.writeString(image.getUri());
        String title = image.getTitle();
        if (title != null) {
            generator.writeFieldName(FIELD_TITLE);
            generator.writeString(title);
        }
        generator.writeFieldName(FIELD_WIDTH);
        generator.writeNumber(image.getWidth());
        generator.writeFieldName(FIELD_HEIGHT);
        generator.writeNumber(image.getHeight());
        generator.writeFieldName(FIELD_SIZE);
        generator.writeString(image.getSize().name());
        generator.writeEndObject();
    }
    
    //////////////////////////////////////////////////
    // Deserialization
    //////////////////////////////////////////////////

    public MediaItem deserialize(JsonParser parser) throws IOException
    {
        MediaItem mc = new MediaItem();
        if (parser.nextToken() != JsonToken.START_OBJECT) {
            reportIllegal(parser, JsonToken.START_OBJECT);
        }
        // first fast version when field-order is as expected
        if (parser.nextFieldName(FIELD_CONTENT)) {
            mc.setContent(readContent(parser));
            if (parser.nextFieldName(FIELD_IMAGES)) {
                mc.setImages(readImages(parser));
                parser.nextToken();
                verifyCurrent(parser, JsonToken.END_OBJECT);
                return mc;
            }
        }
        // and fallback if order was changed
        for (; parser.currentToken() == JsonToken.FIELD_NAME; parser.nextToken()) {
            String field = parser.getCurrentName();
            Integer I = fieldToIndex.get(field);
            if (I != null) {
                switch (I) {
                case FIELD_IX_CONTENT:
                    mc.setContent(readContent(parser));
                    continue;
                case FIELD_IX_IMAGES:
                    mc.setImages(readImages(parser));
                    continue;
                }
            }
            throw new IllegalStateException("Unexpected field '"+field+"' for `MediaItem`");
        }
        verifyCurrent(parser, JsonToken.END_OBJECT);
        
        if (mc.getContent() == null) throw new IllegalStateException("Missing field: " + FIELD_CONTENT);
        if (mc.getImages() == null) mc.setImages(Collections.emptyList());
        
        return mc;
    }

    private MediaContent readContent(JsonParser parser) throws IOException
    {
        if (parser.nextToken() != JsonToken.START_OBJECT) {
            reportIllegal(parser, JsonToken.START_OBJECT);
        }
        MediaContent media = new MediaContent();
        boolean haveWidth = false;
        boolean haveHeight = false;
        boolean haveDuration = false;
        boolean haveSize = false;
        
        // As with above, first fast path
        if (parser.nextFieldName(FIELD_PLAYER)) {
            media.setPlayer(MediaContent.Player.find(parser.nextTextValue()));
            if (parser.nextFieldName(FIELD_URI)) {
                media.setUri(parser.nextTextValue());
                if (parser.nextFieldName(FIELD_TITLE)) {
                    media.setTitle(parser.nextTextValue());
                    if (parser.nextFieldName(FIELD_WIDTH)) {
                        haveWidth = true;
                        media.setWidth(parser.nextIntValue(-1));
                        if (parser.nextFieldName(FIELD_HEIGHT)) {
                            haveHeight = true;
                            media.setHeight(parser.nextIntValue(-1));
                            if (parser.nextFieldName(FIELD_FORMAT)) {
                                media.setFormat(parser.nextTextValue());
                                if (parser.nextFieldName(FIELD_DURATION)) {
                                    haveDuration = true;
                                    media.setDuration(parser.nextLongValue(-1L));
                                    if (parser.nextFieldName(FIELD_SIZE)) {
                                        haveSize = true;
                                        media.setSize(parser.nextLongValue(-1L));
                                        if (parser.nextFieldName(FIELD_BITRATE)) {
                                            media.setBitrate(parser.nextIntValue(-1));
                                            if (parser.nextFieldName(FIELD_COPYRIGHT)) {
                                                media.setCopyright(parser.nextTextValue());
                                                if (parser.nextFieldName(FIELD_PERSONS)) {
                                                    media.setPersons(readPersons(parser));
                                                    parser.nextToken();
                                                    verifyCurrent(parser, JsonToken.END_OBJECT);
                                                    return media;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // and if something reorder or missing, general loop:

        for (; parser.currentToken() == JsonToken.FIELD_NAME; parser.nextToken()) {
            String field = parser.getCurrentName();
            Integer I = fieldToIndex.get(field);
            if (I != null) {
                switch (I) {
                case FIELD_IX_PLAYER:
                    media.setPlayer(MediaContent.Player.find(parser.nextTextValue()));
                    continue;
                case FIELD_IX_URI:
                    media.setUri(parser.nextTextValue());
                    continue;
                case FIELD_IX_TITLE:
                    media.setTitle(parser.nextTextValue());
                    continue;
                case FIELD_IX_WIDTH:
                    media.setWidth(parser.nextIntValue(-1));
                    haveWidth = true;
                    continue;
                case FIELD_IX_HEIGHT:
                    media.setHeight(parser.nextIntValue(-1));
                    haveHeight = true;
                    continue;
                case FIELD_IX_FORMAT:
                    media.setFormat(parser.nextTextValue());
                    continue;
                case FIELD_IX_DURATION:
                    media.setDuration(parser.nextLongValue(-1L));
                    haveDuration = true;
                    continue;
                case FIELD_IX_SIZE:
                    media.setSize(parser.nextLongValue(-1L));
                    haveSize = true;
                    continue;
                case FIELD_IX_BITRATE:
                    media.setBitrate(parser.nextIntValue(-1));
                    continue;
                case FIELD_IX_PERSONS:
                    media.setPersons(readPersons(parser));
                    continue;
                case FIELD_IX_COPYRIGHT:
                    media.setCopyright(parser.nextTextValue());
                    continue;
                }
            }
            throw new IllegalStateException("Unexpected field '"+field+"'");
        }
        verifyCurrent(parser, JsonToken.END_OBJECT);
        
        if (media.getUri() == null) throw new IllegalStateException("Missing field: " + FIELD_URI);
        if (!haveWidth) throw new IllegalStateException("Missing field: " + FIELD_WIDTH);
        if (!haveHeight) throw new IllegalStateException("Missing field: " + FIELD_HEIGHT);
        if (media.getFormat() == null) throw new IllegalStateException("Missing field: " + FIELD_FORMAT);
        if (!haveDuration) throw new IllegalStateException("Missing field: " + FIELD_DURATION);
        if (!haveSize) throw new IllegalStateException("Missing field: " + FIELD_SIZE);
        if (media.getPersons() == null) {
            media.setPersons(Collections.emptyList());
        }
        if (media.getPlayer() == null) throw new IllegalStateException("Missing field: " + FIELD_PLAYER);
        return media;
    }

    private List<Image> readImages(JsonParser parser) throws IOException
    {
        if ((parser.nextToken() != JsonToken.START_ARRAY)
            && !parser.isExpectedStartArrayToken()) {
            reportIllegal(parser, JsonToken.START_ARRAY);
        }
        List<Image> images = new ArrayList<Image>();
        while (parser.nextToken() == JsonToken.START_OBJECT) {
            images.add(readImage(parser));
        }
        verifyCurrent(parser, JsonToken.END_ARRAY);
        return images;
    }

    private List<String> readPersons(JsonParser parser) throws IOException
    {
        if ((parser.nextToken() != JsonToken.START_ARRAY)
                && !parser.isExpectedStartArrayToken()) {
            reportIllegal(parser, JsonToken.START_ARRAY);
        }
        List<String> persons = new ArrayList<String>();
        String str;
        while ((str = parser.nextTextValue()) != null) {
            persons.add(str);
        }
        verifyCurrent(parser, JsonToken.END_ARRAY);
        return persons;
    }                
    
    private Image readImage(JsonParser parser) throws IOException
    {
        boolean haveWidth = false;
        boolean haveHeight = false;
        Image image = new Image();
        if (parser.nextFieldName(FIELD_URI)) {
            image.setUri(parser.nextTextValue());
            if (parser.nextFieldName(FIELD_TITLE)) {
                image.setTitle(parser.nextTextValue());
                if (parser.nextFieldName(FIELD_WIDTH)) {
                    image.setWidth(parser.nextIntValue(-1));
                    haveWidth = true;
                    if (parser.nextFieldName(FIELD_HEIGHT)) {
                        image.setHeight(parser.nextIntValue(-1));
                        haveHeight = true;
                        if (parser.nextFieldName(FIELD_SIZE)) {
                            image.setSize(Size.find(parser.nextTextValue()));
                            parser.nextToken();
                            verifyCurrent(parser, JsonToken.END_OBJECT);
                            return image;
                        }
                    }
                }
            }
        }

        for (; parser.currentToken() == JsonToken.FIELD_NAME; parser.nextToken()) {
            String field = parser.getCurrentName();
            // read value token (or START_ARRAY)
            parser.nextToken();
            Integer I = fieldToIndex.get(field);
            if (I != null) {
                switch (I) {
                case FIELD_IX_URI:
                    image.setUri(parser.getText());
                    continue;
                case FIELD_IX_TITLE:
                    image.setTitle(parser.getText());
                    continue;
                case FIELD_IX_WIDTH:
                    image.setWidth(parser.getIntValue());
                    haveWidth = true;
                    continue;
                case FIELD_IX_HEIGHT:
                    image.setHeight(parser.getIntValue());
                    haveHeight = true;
                    continue;
                case FIELD_IX_SIZE:
                    image.setSize(Size.find(parser.getText()));
                    continue;
                }
            }
            throw new IllegalStateException("Unexpected field '"+field+"'");
        }
        
        if (image.getUri() == null) throw new IllegalStateException("Missing field: " + FIELD_URI);
        if (!haveWidth) throw new IllegalStateException("Missing field: " + FIELD_WIDTH);
        if (!haveHeight) throw new IllegalStateException("Missing field: " + FIELD_HEIGHT);
        if (image.getSize() == null) throw new IllegalStateException("Missing field: " + FIELD_SIZE);

        verifyCurrent(parser, JsonToken.END_OBJECT);
        
        return image;
    }
    
    private final void verifyCurrent(JsonParser parser, JsonToken expToken) throws IOException
    {   
        if (parser.currentToken() != expToken) {
            reportIllegal(parser, expToken);
        }
    }

    private void reportIllegal(JsonParser parser, JsonToken expToken) throws IOException
    {
        JsonToken curr = parser.currentToken();
        String msg = "Expected token "+expToken+"; got "+curr;
        if (curr == JsonToken.FIELD_NAME) {
            msg += " (current field name '"+parser.getCurrentName()+"')";
        }
        msg += ", location: "+parser.getTokenLocation();
        throw new IllegalStateException(msg);
    }
}

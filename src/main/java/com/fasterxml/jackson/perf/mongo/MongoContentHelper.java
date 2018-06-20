package com.fasterxml.jackson.perf.mongo;


import java.util.Map;

import org.bson.BasicBSONEncoder;
import org.bson.BasicBSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

public class MongoContentHelper
{
    public final static MongoContentHelper instance = new MongoContentHelper();

    private final Map<String,Object> stdMediaItemAsMap;

    private final byte[] stdMediaItemAsBSON;

    @SuppressWarnings("unchecked")
    private MongoContentHelper() {
        MediaItem item = MediaItems.stdMediaItem();
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            stdMediaItemAsMap = (Map<String,Object>) jsonMapper.convertValue(item, Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        BasicBSONObject root = new BasicBSONObject(stdMediaItemAsMap);
        stdMediaItemAsBSON = new BasicBSONEncoder().encode(root);
    }

    public Map<String,Object> mediaItemAsMap() {
        return stdMediaItemAsMap;
    }

    public byte[] mediaItemAsBSON() {
        return stdMediaItemAsBSON;
    }
}

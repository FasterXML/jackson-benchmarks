package com.fasterxml.jackson.manualtest;

import com.fasterxml.jackson.core.json.JsonFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

public class MediaItemWriteJson
    extends ObjectWriterTestBase<MediaItem, MediaItem>
{
    @Override
    protected int targetSizeMegs() { return 20; }

    public static void main(String[] args) throws Exception
    {
//    	final boolean USE_BYTES = true;
        final boolean USE_AFTERBURNER = true;

        if (args.length != 0) {
            System.err.println("Usage: java ...");
            System.exit(1);
        }
        String desc = "JSON";
        MediaItem input = MediaItems.stdMediaItem();
        ObjectMapper m = _mapper(JsonMapper.builder(new JsonFactory()), USE_AFTERBURNER);
        if (USE_AFTERBURNER) {
            desc += "+Afterburner";
        }
        new MediaItemWriteJson().test(m,
        		desc+"#1", input, MediaItem.class,
        		desc+"#2", input, MediaItem.class);
    }
}

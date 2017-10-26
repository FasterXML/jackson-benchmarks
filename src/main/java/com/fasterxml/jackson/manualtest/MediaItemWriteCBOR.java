package com.fasterxml.jackson.manualtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

public class MediaItemWriteCBOR
    extends ObjectWriterTestBase<MediaItem, MediaItem>
{
    @Override
    protected int targetSizeMegs() { return 20; }

    public static void main(String[] args) throws Exception
    {
//    	final boolean USE_AFTERBURNER = true;
        final boolean USE_AFTERBURNER = false;

        if (args.length != 0) {
            System.err.println("Usage: java ...");
            System.exit(1);
        }
        String desc = "CBOR";
        MediaItem input = MediaItems.stdMediaItem();
        ObjectMapper m = new ObjectMapper(new CBORFactory());
        if (USE_AFTERBURNER) {
            m.registerModule(new AfterburnerModule());
            desc += "+Afterburner";
        }
        new MediaItemWriteCBOR().test(m,
        		desc+"#1", input, MediaItem.class,
        		desc+"#2", input, MediaItem.class);
    }
}

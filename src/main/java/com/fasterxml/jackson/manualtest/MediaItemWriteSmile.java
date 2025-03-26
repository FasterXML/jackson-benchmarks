package com.fasterxml.jackson.manualtest;

import tools.jackson.databind.ObjectMapper;

import tools.jackson.dataformat.smile.SmileFactory;
import tools.jackson.dataformat.smile.SmileMapper;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

public class MediaItemWriteSmile
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
        String desc = "Smile";
        MediaItem input = MediaItems.stdMediaItem();
        ObjectMapper m = _mapper(SmileMapper.builder(new SmileFactory()), USE_AFTERBURNER);
        if (USE_AFTERBURNER) {
            desc += "+Afterburner";
        }
        new MediaItemWriteSmile().test(m,
        		desc+"#1", input, MediaItem.class,
        		desc+"#2", input, MediaItem.class);
    }
}

package com.fasterxml.jackson.perf;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;
import com.fasterxml.jackson.perf.util.NopOutputStream;

public abstract class WritePerfBasicJackson
	implements WritePerfTestBasic
{
    protected final ObjectWriter MEDIA_ITEM_WRITER;

    protected final MediaItem item;
    
    protected WritePerfBasicJackson(ObjectMapper mapper)
    {
    	MEDIA_ITEM_WRITER = mapper.writerWithType(MediaItem.class);
    	item = MediaItems.stdMediaItem();
    }

    /*
    /**********************************************************************
    /* Typed reading tests
    /**********************************************************************
     */

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writePojoMediaItem(BlackHole bh) throws Exception {
        bh.consume(write(item, MEDIA_ITEM_WRITER));
    }

    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    protected final int write(MediaItem item, ObjectWriter w) throws IOException {
    	NopOutputStream out = new NopOutputStream();
    	w.writeValue(out, item);
    	return out.size();
    }
}

package com.fasterxml.jackson.perf;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;
import com.fasterxml.jackson.perf.util.NopOutputStream;

public abstract class WritePerfBasicJackson
	implements WritePerfTestBasic
{
    protected final ObjectWriter MEDIA_ITEM_WRITER;

    protected final MediaItem item;
    
    protected WritePerfBasicJackson(ObjectMapper mapper) {
    	this(mapper, null);
    }

    protected WritePerfBasicJackson(ObjectMapper mapper, FormatSchema schema)
    {
        ObjectWriter w = mapper.writerWithType(MediaItem.class);
        if (schema != null) {
        	w = w.withSchema(schema);
        }
        MEDIA_ITEM_WRITER = w;
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

    @SuppressWarnings("resource")
    protected final int write(MediaItem value, ObjectWriter w) throws IOException {
        NopOutputStream out = new NopOutputStream();
        w.writeValue(out, value);
        return out.size();
    }
}

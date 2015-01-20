package com.fasterxml.jackson.perf;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.model.MediaItems;
import com.fasterxml.jackson.perf.util.NopOutputStream;

public abstract class WritePerfBasicJackson<T>
	implements WritePerfTestBasic
{
    protected final ObjectWriter MEDIA_ITEM_WRITER;

    protected final T item;
    
    protected WritePerfBasicJackson(ObjectMapper mapper) {
    	this(mapper, null);
    }

    @SuppressWarnings("unchecked")
    protected WritePerfBasicJackson(ObjectMapper mapper, FormatSchema schema) {
        this(mapper, schema, (T) MediaItems.stdMediaItem());
    }

    protected WritePerfBasicJackson(ObjectMapper mapper, FormatSchema schema, T value)
    {
        ObjectWriter w = mapper.writerFor(value.getClass());
        if (schema != null) {
            w = w.with(schema);
        }
        MEDIA_ITEM_WRITER = w;
        item = value;
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

    protected final int write(T value, ObjectWriter w) throws IOException {
        NopOutputStream out = new NopOutputStream();
        w.writeValue(out, value);
        return out.size();
    }
}

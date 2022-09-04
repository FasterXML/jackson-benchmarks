package com.fasterxml.jackson.perf;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import tools.jackson.core.FormatSchema;
import tools.jackson.databind.*;
import tools.jackson.databind.cfg.MapperBuilder;
import tools.jackson.module.afterburner.AfterburnerModule;
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
    /* Typed writing tests
    /**********************************************************************
     */

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writePojoMediaItem(Blackhole bh) throws Exception {
        bh.consume(write(item, MEDIA_ITEM_WRITER));
    }

    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    @SuppressWarnings("resource")
    protected final int write(Object value, ObjectWriter w) throws IOException {
        NopOutputStream out = new NopOutputStream();
        w.writeValue(out, value);
        return out.size();
    }

    protected static MapperBuilder<?,?> _withAfterburner(MapperBuilder<?,?> b) {
        return b.addModule(new AfterburnerModule());
    }
}

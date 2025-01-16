package com.fasterxml.jackson.perf;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

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

    // Let's print process ID for profiler
    @Setup
    public void setup() {
        // On Java 9+ could use:
//        long pid = ProcessHandle.current().pid();
        // But for now:
        String name = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        
        System.out.print("[JMH Process ID: " + pid + "]");
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
}

package com.fasterxml.jackson.perf.json;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import tools.jackson.jr.ob.JSON;

import com.fasterxml.jackson.perf.*;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;
import com.fasterxml.jackson.perf.util.NopOutputStream;

@State(value = Scope.Benchmark)
public class JacksonJrStdWriteVanilla implements WritePerfTestBasic
{
    private static final JSON json = JSON.std;

    private static final MediaItem item = MediaItems.stdMediaItem();

    // NOTE: see WritePerfBaseFullJackson for explanation on this one:
    protected final AtomicReference<Object> untypedRef = new AtomicReference<Object>();

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writePojoMediaItem(Blackhole bh) throws Exception
    {
        bh.consume(write(item, json));
    }

    @Benchmark
    public void writeUntypedMediaItem(Blackhole bh) throws Exception {
        Object value = untypedRef.get();
        if (value == null) {
            value = json.mapFrom(json.asString(item));
            untypedRef.set(value);
        }
        bh.consume(write(value, json));
    }
    
    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    @SuppressWarnings("resource")
    protected final int write(Object value, JSON writer) throws IOException {
        NopOutputStream out = new NopOutputStream();
        writer.write(value, out);
        return out.size();
    }
}

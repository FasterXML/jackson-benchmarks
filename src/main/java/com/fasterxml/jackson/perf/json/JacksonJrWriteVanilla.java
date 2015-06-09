package com.fasterxml.jackson.perf.json;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.perf.*;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;
import com.fasterxml.jackson.perf.util.NopOutputStream;

public class JacksonJrWriteVanilla implements WritePerfTestBasic
{
    private static final JSON json = JSON.std;

    private static final MediaItem item = MediaItems.stdMediaItem();

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writePojoMediaItem(Blackhole bh) throws Exception
    {
        bh.consume(write(item, json));
    }

    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    @SuppressWarnings("resource")
    protected final int write(MediaItem value, JSON writer) throws IOException {
        NopOutputStream out = new NopOutputStream();
        writer.write(value, out);
        return out.size();
    }    
}

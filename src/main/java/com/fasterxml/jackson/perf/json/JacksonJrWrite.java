package com.fasterxml.jackson.perf.json;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.perf.*;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;
import com.fasterxml.jackson.perf.util.NopOutputStream;

public class JacksonJrWrite implements WritePerfTestBasic
{
    private static final JSON json = JSON.std;

    private static final MediaItem item = MediaItems.stdMediaItem();
    
    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writePojoMediaItem(BlackHole bh) throws Exception
    {
        bh.consume(write(item, json));
    }

    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    protected final int write(MediaItem value, JSON writer) throws IOException {
        NopOutputStream out = new NopOutputStream();
        writer.write(value, out);
        return out.size();
    }    
}

package com.fasterxml.jackson.perf.json;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.jr.ob.JSON;

import com.fasterxml.jackson.perf.PerfTestLimited;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@OutputTimeUnit(TimeUnit.SECONDS)
public class JacksonJrDatabind
	implements PerfTestLimited
{
//    @State(Scope.Group) // Thread, Group or Benchmark
    private static final JSON json = JSON.std;
    
    // null; will use JSON input as-is
    private final static InputConverter NO_OP = new InputConverter(null);

    public JacksonJrDatabind() { }

    @GenerateMicroBenchmark
    @Override
    public void readPojoMediaItem(BlackHole bh) throws Exception {
        bh.consume(read(NO_OP.bytesForMediaItem()));
    }

    private MediaItem read(byte[] input) throws Exception
    {
        try {
            return json.beanFrom(MediaItem.class, input);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}

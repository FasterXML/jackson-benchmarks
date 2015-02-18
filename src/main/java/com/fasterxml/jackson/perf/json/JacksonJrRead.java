package com.fasterxml.jackson.perf.json;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.perf.ReadPerfTestBasic;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

@OutputTimeUnit(TimeUnit.SECONDS)
public class JacksonJrRead
	implements ReadPerfTestBasic
{
//    @State(Scope.Group) // Thread, Group or Benchmark
    private static final JSON json = JSON.std;
    
    private static byte[] _mediaItemBytes;
    static {
        try {
    		    _mediaItemBytes = json.asBytes(MediaItems.stdMediaItem());
        } catch (IOException e) {
    		    throw new RuntimeException(e);
        }
    }

    public JacksonJrRead() { }

    @GenerateMicroBenchmark
    @Override
    public void readPojoMediaItem(BlackHole bh) throws Exception {
        bh.consume(read(_mediaItemBytes));
    }

    // part of 'full' test, but we don't implement that in its entirety
    @GenerateMicroBenchmark
    public void readTreeMediaItem(BlackHole bh) throws Exception {
        bh.consume(readTree(_mediaItemBytes));
    }

    private MediaItem read(byte[] input) throws Exception {
        return json.beanFrom(MediaItem.class, input);
    }

    private Object readTree(byte[] input) throws Exception {
        return json.anyFrom(input);
    }
}

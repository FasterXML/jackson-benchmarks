package com.fasterxml.jackson.perf.json;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.perf.ReadPerfTestBasic;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class JacksonJrReadVanilla
    implements ReadPerfTestBasic
{
    static final JSON json = JSON.std;

    static byte[] _mediaItemBytes;
    static {
        try {
            _mediaItemBytes = json.asBytes(MediaItems.stdMediaItem());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JacksonJrReadVanilla() { }

    @Benchmark
    @Override
    public void readPojoMediaItem(Blackhole bh) throws Exception {
        bh.consume(read(_mediaItemBytes));
    }

    // part of 'full' test, but we don't implement that in its entirety
    @Benchmark
    public void readTreeMediaItem(Blackhole bh) throws Exception {
        bh.consume(readTree(_mediaItemBytes));
    }

    private MediaItem read(byte[] input) throws Exception {
        return json.beanFrom(MediaItem.class, input);
    }

    private Object readTree(byte[] input) throws Exception {
        return json.anyFrom(input);
    }
}

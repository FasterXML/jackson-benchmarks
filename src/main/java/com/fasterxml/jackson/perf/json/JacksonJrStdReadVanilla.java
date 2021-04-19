package com.fasterxml.jackson.perf.json;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.perf.AuxStateSize;
import com.fasterxml.jackson.perf.ReadPerfTestBasic;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class JacksonJrStdReadVanilla
    implements ReadPerfTestBasic
{
    static final JSON json = JSON.std
            .with(JSON.Feature.FAIL_ON_UNKNOWN_BEAN_PROPERTY)
            // 06-Jul-2016, tatu: important for apples-to-apples comparison:
            .without(JSON.Feature.USE_DEFERRED_MAPS);

    static byte[] _mediaItemBytes;
    static {
        try {
            _mediaItemBytes = json.asBytes(MediaItems.stdMediaItem());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JacksonJrStdReadVanilla() { }

    @Benchmark
    @Override
    public void readPojoMediaItem(Blackhole bh, AuxStateSize size) throws Exception {
        size.set(_mediaItemBytes.length);
        bh.consume(read(_mediaItemBytes));
    }

    // part of 'full' test, but we don't implement that in its entirety
    @Benchmark
    public void readUntypedMediaItem(Blackhole bh) throws Exception {
        bh.consume(readTree(_mediaItemBytes));
    }

    private MediaItem read(byte[] input) throws Exception {
        return json.beanFrom(MediaItem.class, input);
    }

    private Object readTree(byte[] input) throws Exception {
        return json.anyFrom(input);
    }
}

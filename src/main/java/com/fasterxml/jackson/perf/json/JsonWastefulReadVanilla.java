package com.fasterxml.jackson.perf.json;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

/**
 * Specialized variant where the ObjectMapper is newly constructed each time.
 * Mainly used to given an idea of startup overhead, and allow profiling it.
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonWastefulReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // pass non-null ObjectMapper: will remove whitespace, if any
    private final static InputConverter JSON_CONV = InputConverter.stdConverter(MAPPER);

    public JsonWastefulReadVanilla() {
        super(MediaItem.class, JSON_CONV, MAPPER);
    }

    @Override
    public void readPojoMediaItem(Blackhole bh) throws Exception {
        bh.consume(read(MINIMAL_CONV.mediaItemAsBytes()));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readNodeMediaItem(Blackhole bh) throws Exception {
        bh.consume(readTree(JSON_CONV.mediaItemAsBytes()));
    }
    
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readUntypedMediaItem(Blackhole bh) throws Exception {
        bh.consume(readUntyped(JSON_CONV.mediaItemAsBytes()));
    }

    protected Object read(byte[] data) throws Exception {
        return new ObjectMapper().readValue(data, MediaItem.class);
    }

    protected JsonNode readTree(byte[] data) throws Exception {
        return new ObjectMapper().readTree(data);
    }

    protected Object readUntyped(byte[] data) throws Exception {
        return new ObjectMapper().readValue(data, Map.class);
    }
}

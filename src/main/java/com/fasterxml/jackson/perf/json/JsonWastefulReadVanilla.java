package com.fasterxml.jackson.perf.json;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.perf.AuxStateSize;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

/**
 * Specialized variant where the ObjectMapper is newly constructed each time.
 * Mainly used to given an idea of startup overhead, and allow profiling it.
 *<p>
 * Note: input as {@code String}, no intern()ing, to reduce that overhead
 * (to allow verifying other optimizations)
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonWastefulReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new JsonMapper();

    // pass non-null ObjectMapper: will remove whitespace, if any
    private final static InputConverter JSON_CONV = InputConverter.stdConverter(MAPPER);

    public JsonWastefulReadVanilla() {
        super(MediaItem.class, JSON_CONV, MAPPER);
    }

    @Override
    public void readPojoMediaItem(Blackhole bh, AuxStateSize size) throws Exception {
        final String input = MINIMAL_CONV.mediaItemAsString();
        size.set(input.length());
        bh.consume(read(input));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readNodeMediaItem(Blackhole bh) throws Exception {
        bh.consume(readTree(JSON_CONV.mediaItemAsString()));
    }
    
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readUntypedMediaItem(Blackhole bh) throws Exception {
        bh.consume(readUntyped(JSON_CONV.mediaItemAsString()));
    }

    protected Object read(String data) throws Exception {
        return mapper().readValue(data, MediaItem.class);
    }

    protected JsonNode readTree(String data) throws Exception {
        return mapper().readTree(data);
    }

    protected Object readUntyped(String data) throws Exception {
        // 16-Jun-2019, tatu: `Object` is bit faster, skips resolution of `Map` probably?
//        return new ObjectMapper().readValue(data, Map.class);
        return mapper().readValue(data, Object.class);
    }

    @SuppressWarnings("deprecation")
    private final ObjectMapper mapper() {
        // 3.x disables intern()ing by default so no need to change
        return new JsonMapper();
    }
}

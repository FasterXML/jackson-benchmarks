package com.fasterxml.jackson.perf.json;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.WritePerfTestFull;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;
import com.fasterxml.jackson.perf.util.NopOutputStream;

/**
 * Specialized variant where the ObjectMapper is newly constructed each time.
 * Mainly used to given an idea of startup overhead, and allow profiling it.
 *<p>
 * Note: input as {@code String}, no intern()ing, to reduce that overhead
 * (to allow verifying other optimizations)
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonWastefulWriteVanilla
    implements WritePerfTestFull
{
    private final static ObjectMapper MAPPER = new ObjectMapper();

    private final MediaItem _value;

    private final JsonNode _node;

    private final Object _untyped;

    public JsonWastefulWriteVanilla() {
        _value = MediaItems.stdMediaItem();
        _node = MAPPER.valueToTree(_value);
        _untyped = MAPPER.convertValue(_value, Map.class);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writeUntypedMediaItem(Blackhole bh) throws Exception {
        bh.consume(write(_untyped));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writeNodeMediaItem(Blackhole bh) throws Exception {
        bh.consume(write(_node));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writePojoMediaItem(Blackhole bh) throws Exception {
        bh.consume(write(_value));
    }

    private int write(Object value) throws Exception {
        NopOutputStream out = new NopOutputStream();
        new ObjectMapper().writeValue(out, value);
        return out.size();
    }
}

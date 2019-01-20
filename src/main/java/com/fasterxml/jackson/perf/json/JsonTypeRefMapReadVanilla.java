package com.fasterxml.jackson.perf.json;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

/**
 * Specialized variant where we read content similar to "Untyped", but actually
 * explicitly pass {@code TypeReference} with each call; this to see relative
 * overhead for resolving generic types.
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonTypeRefMapReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new JsonMapper();

    private final static InputConverter JSON_CONV = InputConverter.stdConverter(MAPPER);

    public JsonTypeRefMapReadVanilla() {
        super(MediaItem.class, JSON_CONV, MAPPER);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readMap(Blackhole bh) throws Exception {
        bh.consume(read(MINIMAL_CONV.mediaItemAsBytes(),
                new TypeReference<Map<String, Object>>() {}));
    }

    protected Object read(byte[] data, TypeReference<?> type) throws Exception {
        return MAPPER.readValue(data, type);
    }
}

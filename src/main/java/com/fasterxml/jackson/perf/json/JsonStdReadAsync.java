package com.fasterxml.jackson.perf.json;

import org.openjdk.jmh.annotations.State;

import java.io.IOException;

import org.openjdk.jmh.annotations.Scope;

import tools.jackson.core.*;
import tools.jackson.core.async.ByteArrayFeeder;
import tools.jackson.core.json.JsonWriteFeature;
import tools.jackson.databind.*;
import tools.jackson.databind.json.JsonMapper;

import com.fasterxml.jackson.perf.*;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

/**
 * Specialized variant that uses non-blocking (async) parsing, but,
 * passes the whole data in one chunk. This is necessary to make things
 * work at all via data-binding, although may not be optimal representation
 * of typical usage.
 */
@State(Scope.Thread)
public class JsonStdReadAsync
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = JsonMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)
            .disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES)
            .build();

    private final static InputConverter CONV = InputConverter.stdConverter(MAPPER);

    public JsonStdReadAsync() {
        super(MediaItem.class, CONV, MAPPER);
    }

    @Override
    protected Object read(byte[] input, ObjectReader reader) throws IOException {
        JsonParser p = MAPPER.createNonBlockingByteArrayParser();
        ByteArrayFeeder feeder = (ByteArrayFeeder) p.nonBlockingInputFeeder();
        feeder.feedInput(input, 0, input.length);
        feeder.endOfInput();
        Object result = reader.readValue(p);
        p.close();
        return result;
    }
}

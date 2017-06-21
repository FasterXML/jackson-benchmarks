package com.fasterxml.jackson.perf.json;

import org.openjdk.jmh.annotations.State;

import java.io.IOException;

import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.async.ByteArrayFeeder;

import com.fasterxml.jackson.databind.*;

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
    private final static JsonFactory _factory = new JsonFactory();

    private static final ObjectMapper MAPPER = new ObjectMapper(_factory);

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public JsonStdReadAsync() {
        super(MediaItem.class, SMILES, MAPPER);
    }

    @Override
    protected Object read(byte[] input, ObjectReader reader) throws IOException {
        JsonParser p = _factory.createNonBlockingByteArrayParser();
        ((ByteArrayFeeder) p.getNonBlockingInputFeeder()).feedInput(input, 0, input.length);
        Object result = reader.readValue(p);
        p.close();
        return result;
    }
}

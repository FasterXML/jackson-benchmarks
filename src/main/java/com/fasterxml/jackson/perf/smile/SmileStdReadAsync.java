package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;

import org.openjdk.jmh.annotations.Scope;

import tools.jackson.core.async.ByteArrayFeeder;
import tools.jackson.core.JsonParser;

import tools.jackson.databind.*;
import tools.jackson.dataformat.smile.SmileMapper;
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
public class SmileStdReadAsync
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = SmileMapper.builder()
            .build();

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public SmileStdReadAsync() {
        super(MediaItem.class, SMILES, MAPPER);
    }

    @Override
    protected Object read(byte[] input, ObjectReader reader) {
        JsonParser p = MAPPER.createNonBlockingByteArrayParser();
        ((ByteArrayFeeder) p.nonBlockingInputFeeder()).feedInput(input, 0, input.length);
        Object result = reader.readValue(p);
        p.close();
        return result;
    }
}

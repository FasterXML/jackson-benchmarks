package com.fasterxml.jackson.perf.json;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.perf.*;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

/**
 * Specialized variant where the ObjectMapper is newly constructed each time.
 * Mainly used to given an idea of startup overhead, and allow profiling it.
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JacksonJrWastefulReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // pass non-null ObjectMapper: will remove whitespace, if any
    private final static InputConverter JSON_CONV = InputConverter.stdConverter(MAPPER);

    public JacksonJrWastefulReadVanilla() {
        super(MediaItem.class, JSON_CONV, MAPPER);
    }

    @Override
    public void readPojoMediaItem(Blackhole bh/*, AuxStateSize size*/) throws Exception {
        final byte[] input = MINIMAL_CONV.mediaItemAsBytes();
//        size.set(input.length);
        bh.consume(read(input));
    }

    protected Object read(byte[] data) throws Exception {
        return JSON.std
                .with(JSON.Feature.FAIL_ON_UNKNOWN_BEAN_PROPERTY)
                .without(JSON.Feature.USE_DEFERRED_MAPS)
                .beanFrom(MediaItem.class, data);
    }
}

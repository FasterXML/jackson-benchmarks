package com.fasterxml.jackson.perf.json;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.core.json.JsonFactory;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

// 11-Apr-2018, tatu: Note that this test should NOT have different performance
//    in 3.0.0, as intern()ing is not enabled by default any more.
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonNoInternReadVanilla
    extends ReadPerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper MAPPER;
    static {
        JsonFactory f = JsonFactory.builder()
                .disable(JsonFactory.Feature.INTERN_FIELD_NAMES)
                .build();
        MAPPER = new ObjectMapper(f);
    }

    // pass non-null ObjectMapper: will remove whitespace, if any
    private final static InputConverter JSON_CONV = InputConverter.stdConverter(MAPPER);

    // NOTE: to _RETAIN_ whitespace, we'd use:
//    private final static InputConverter JSON_CONV = InputConverter.nopConverter(MAPPER);
    
    public JsonNoInternReadVanilla() {
        super(MediaItem.class, JSON_CONV, MAPPER);
    }
}

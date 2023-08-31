package com.fasterxml.jackson.perf.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonNoCanonicalizeReadVanilla
    extends ReadPerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = JsonMapper.builder(
            JsonFactory.builder()
                    .disable(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES)
                    .build())
            .build();

    // pass non-null ObjectMapper: will remove whitespace, if any
    private final static InputConverter JSON_CONV = InputConverter.stdConverter(MAPPER);

    // NOTE: to _RETAIN_ whitespace, we'd use:
//    private final static InputConverter JSON_CONV = InputConverter.nopConverter(MAPPER);

    public JsonNoCanonicalizeReadVanilla() {
        super(MediaItem.class, JSON_CONV, MAPPER);
    }
}

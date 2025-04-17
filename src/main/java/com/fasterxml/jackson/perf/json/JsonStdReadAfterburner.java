package com.fasterxml.jackson.perf.json;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.core.json.JsonWriteFeature;
import tools.jackson.databind.*;
import tools.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class JsonStdReadAfterburner
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = _withAfterburner(JsonMapper.builder())
            .disable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)
            .disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES)
            .build();

    private final static InputConverter NO_OP = InputConverter.stdConverter(MAPPER);

    public JsonStdReadAfterburner() {
        super(MediaItem.class, NO_OP, MAPPER);
    }
}

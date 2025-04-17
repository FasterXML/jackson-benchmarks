package com.fasterxml.jackson.perf.json;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import tools.jackson.core.json.JsonWriteFeature;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class JsonStdWriteAfterburner extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = _withAfterburner(JsonMapper.builder())
            .disable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)
            .disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES)
            .build();

    public JsonStdWriteAfterburner() {
		super(MAPPER);
	}
}

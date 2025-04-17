package com.fasterxml.jackson.perf.json;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import tools.jackson.core.json.JsonWriteFeature;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import com.fasterxml.jackson.perf.WritePerfBaseFullJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class JsonStdWriteVanilla extends WritePerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper JSON_MAPPER = JsonMapper.builder()
            .disable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)
            .disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES)
            .build();
            
    public JsonStdWriteVanilla() {
        super(JSON_MAPPER);
    }
}

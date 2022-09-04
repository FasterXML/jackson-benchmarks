package com.fasterxml.jackson.perf.json;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Thread)
public class JsonColumnWriteVanilla extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = JsonMapper.builder()
            .annotationIntrospector(new AsArrayIntrospector())
            .build();

    public JsonColumnWriteVanilla() {
        super(MAPPER);
    }
}

package com.fasterxml.jackson.perf.json;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import tools.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class JsonStdWriteAfterburner extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER =
            _withAfterburner(PerfDefaultsForJson.jsonMapperBuilder())
            .build();

    public JsonStdWriteAfterburner() {
		super(MAPPER);
	}
}

package com.fasterxml.jackson.perf.json;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Group) // Thread, Group or Benchmark
public class JsonColumnWriteAfterburner extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.setAnnotationIntrospector(new AsArrayIntrospector());
        MAPPER.registerModule(new AfterburnerModule());
    }

	public JsonColumnWriteAfterburner() {
		super(MAPPER);
	}
}

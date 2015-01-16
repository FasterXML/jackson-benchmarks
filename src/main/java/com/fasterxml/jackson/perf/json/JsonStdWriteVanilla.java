package com.fasterxml.jackson.perf.json;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Group) // Thread, Group or Benchmark
public class JsonStdWriteVanilla extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

	public JsonStdWriteVanilla() {
		super(MAPPER);
	}
}

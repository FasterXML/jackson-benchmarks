package com.fasterxml.jackson.perf.json;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;

@State(Scope.Group) // Thread, Group or Benchmark
public class JsonWriteVanilla extends WritePerfBasicJackson
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

	public JsonWriteVanilla() {
		super(MAPPER);
	}
}

package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Group) // Thread, Group or Benchmark
public class SmileStdWriteVanilla
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER;
    static {
    	SmileFactory f = new SmileFactory();
    	// configure differently?
    	MAPPER = new ObjectMapper(f);
    }

    public SmileStdWriteVanilla() {
        super(MAPPER);
    }
}

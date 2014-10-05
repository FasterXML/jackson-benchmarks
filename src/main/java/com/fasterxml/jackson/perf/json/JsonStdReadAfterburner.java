package com.fasterxml.jackson.perf.json;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Group) // Thread, Group or Benchmark
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonStdReadAfterburner
    extends ReadPerfBaseBasicJackson
{
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

    // pass non-null ObjectMapper: will remove whitespace, if any
    private final static InputConverter NO_OP = InputConverter.stdConverter(MAPPER);

    public JsonStdReadAfterburner() {
        super(NO_OP, MAPPER);
    }
}

package com.fasterxml.jackson.perf.json;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Group) // Thread, Group or Benchmark
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonStdReadWS
    extends ReadPerfBaseFullJackson
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // pass null ObjectMapper: input used exactly as is
    private final static InputConverter NO_OP = InputConverter.nopConverter(MAPPER);

    public JsonStdReadWS() {
        super(NO_OP, MAPPER);
    }
}

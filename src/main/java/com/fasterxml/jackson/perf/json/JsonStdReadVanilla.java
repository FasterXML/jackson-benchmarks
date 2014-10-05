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
public class JsonStdReadVanilla
    extends ReadPerfBaseFullJackson
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // pass non-null ObjectMapper: will remove whitespace, if any
    private final static InputConverter NO_OP = InputConverter.stdConverter(MAPPER);

    public JsonStdReadVanilla() {
        super(NO_OP, MAPPER);
    }
}

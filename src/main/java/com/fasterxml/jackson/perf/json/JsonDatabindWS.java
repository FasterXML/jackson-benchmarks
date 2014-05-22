package com.fasterxml.jackson.perf.json;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.PerfBaseFull;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Thread) // or Group or Benchmark
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonDatabindWS
    extends PerfBaseFull
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // true -> skip conversion to keep whitespace
    private final static InputConverter NO_OP = new InputConverter(MAPPER, true);

    public JsonDatabindWS() {
        super(NO_OP, MAPPER);
    }
}

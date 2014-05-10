package com.fasterxml.jackson.perf.json;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.PerfBase;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Thread) // or Group or Benchmark
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonDatabindCompact
    extends PerfBase
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // false -> convert, removing ws
    private final static InputConverter NO_OP = new InputConverter(MAPPER, false);

    public JsonDatabindCompact() {
        super(NO_OP, MAPPER);
    }
}

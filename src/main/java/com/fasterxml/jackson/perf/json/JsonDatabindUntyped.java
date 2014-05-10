package com.fasterxml.jackson.perf.json;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.perf.PerfBase;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Thread) // or Group or Benchmark
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonDatabindUntyped
    extends PerfBase
{
    private static final ObjectReader JSON_READER = new ObjectMapper().reader(Object.class);

    // we'll use a no-op converter to retain white-space
    private final static InputConverter NO_OP = new InputConverter(null);

    public JsonDatabindUntyped() {
        super(NO_OP, JSON_READER);
    }
}

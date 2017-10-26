package com.fasterxml.jackson.perf.manual;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonManualReadVanilla
    extends ManualReadPerfTest
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final static InputConverter JSON_CONV = InputConverter.stdConverter(MAPPER);

    public JsonManualReadVanilla() {
        super(JSON_CONV, MAPPER.reader());
    }
}

package com.fasterxml.jackson.perf.manual;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class CBORManualReadVanilla
    extends ManualReadPerfTest
{
    private static final ObjectMapper MAPPER = new ObjectMapper(new CBORFactory());

    private final static InputConverter CONV = InputConverter.stdConverter(MAPPER);

    public CBORManualReadVanilla() {
        super(CONV, MAPPER.reader());
    }
}

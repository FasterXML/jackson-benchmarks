package com.fasterxml.jackson.perf.manual;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class SmileManualReadVanilla
    extends ManualReadPerfTest
{
    private static final ObjectMapper MAPPER = new ObjectMapper(new SmileFactory());

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public SmileManualReadVanilla() {
        super(SMILES, MAPPER.reader());
    }
}

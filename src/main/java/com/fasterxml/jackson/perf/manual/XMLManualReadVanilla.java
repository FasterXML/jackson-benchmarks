package com.fasterxml.jackson.perf.manual;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;
import com.fasterxml.jackson.perf.xml.StaxProvider;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class XMLManualReadVanilla
    extends ManualReadPerfTest
{
    private static final ObjectMapper MAPPER = StaxProvider.xmlMapper();

    private final static MinimalInputConverter CONV = MinimalInputConverter.minimalConverter(MAPPER);

    public XMLManualReadVanilla() {
        super(CONV, MAPPER.reader());
    }
}

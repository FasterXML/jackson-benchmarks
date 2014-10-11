package com.fasterxml.jackson.perf.xml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.*;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;

@State(Scope.Group) // Thread, Group or Benchmark
public class XMLStdReadVanilla
    extends ReadPerfBaseBasicJackson
{
    private static final ObjectMapper MAPPER = StaxProvider.xmlMapper();

    private final static MinimalInputConverter XML = MinimalInputConverter.minimalConverter(MAPPER);

    public XMLStdReadVanilla() {
        super(XML, MAPPER);
    }
}

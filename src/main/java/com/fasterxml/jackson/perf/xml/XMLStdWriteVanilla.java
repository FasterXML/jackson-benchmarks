package com.fasterxml.jackson.perf.xml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;

@State(Scope.Group) // Thread, Group or Benchmark
public class XMLStdWriteVanilla
    extends WritePerfBasicJackson
{
    private static final ObjectMapper MAPPER = StaxProvider.xmlMapper();

    public XMLStdWriteVanilla() {
        super(MAPPER);
    }
}

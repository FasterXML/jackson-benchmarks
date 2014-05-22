package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.perf.PerfBaseFull;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Group) // Thread, Group or Benchmark
public class SmileDatabindVanilla
    extends PerfBaseFull
{
    private final static SmileFactory _sf = new SmileFactory();
    
    private static final ObjectMapper MAPPER = new ObjectMapper(_sf);

    private final static InputConverter SMILES = new InputConverter(MAPPER, false);

    public SmileDatabindVanilla() {
        super(SMILES, MAPPER);
    }
}

package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.perf.PerfBase;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Thread) // or Group or Benchmark
public class SmileDatabind
    extends PerfBase
{
    private final static SmileFactory _sf = new SmileFactory();
    
    private static final ObjectMapper MAPPER = new ObjectMapper(_sf);

    private final static InputConverter SMILES = new InputConverter(MAPPER, false);

    public SmileDatabind() {
        super(SMILES, MAPPER);
    }
}

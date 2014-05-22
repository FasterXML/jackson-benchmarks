package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.PerfBaseLimited;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Group) // Thread, Group or Benchmark
public class SmileDatabindAfterburner
    extends PerfBaseLimited
{
    private final static SmileFactory _sf = new SmileFactory();
    
    private static final ObjectMapper MAPPER = new ObjectMapper(_sf);
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

    private final static InputConverter SMILES = new InputConverter(MAPPER, false);

    public SmileDatabindAfterburner() {
        super(SMILES, MAPPER);
    }
}

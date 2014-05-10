package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.perf.PerfBase;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Thread) // or Group or Benchmark
public class SmileDatabindUntyped
    extends PerfBase
{
    private final static SmileFactory _sf = new SmileFactory();
    
    private static final ObjectReader SMILE_READER = new ObjectMapper(_sf).reader(Object.class);

    private final static InputConverter SMILES = new InputConverter(_sf);

    public SmileDatabindUntyped() {
        super(SMILES, SMILE_READER);
    }
}

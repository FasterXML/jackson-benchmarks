package com.fasterxml.jackson.perf.cbor;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.PerfBaseLimitedJackson;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Group) // Thread, Group or Benchmark
public class CBORDatabindAfterburner
    extends PerfBaseLimitedJackson
{
    private final static CBORFactory _cf = new CBORFactory();
    
    private static final ObjectMapper MAPPER = new ObjectMapper(_cf);
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public CBORDatabindAfterburner() {
        super(SMILES, MAPPER);
    }
}

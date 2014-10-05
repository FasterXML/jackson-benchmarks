package com.fasterxml.jackson.perf.cbor;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;

@State(Scope.Group) // Thread, Group or Benchmark
public class CBORStdReadAfterburner
    extends ReadPerfBaseBasicJackson
{
    private final static CBORFactory _cf = new CBORFactory();
    
    private static final ObjectMapper MAPPER = new ObjectMapper(_cf);
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public CBORStdReadAfterburner() {
        super(SMILES, MAPPER);
    }
}

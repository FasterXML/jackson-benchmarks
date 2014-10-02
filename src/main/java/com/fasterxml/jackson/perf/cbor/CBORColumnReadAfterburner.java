package com.fasterxml.jackson.perf.cbor;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Group) // Thread, Group or Benchmark
public class CBORColumnReadAfterburner
    extends ReadPerfBaseBasicJackson
{
    private final static CBORFactory _cf = new CBORFactory();
    
    private static final ObjectMapper MAPPER = new ObjectMapper(_cf);
    static {
        MAPPER.registerModule(new AfterburnerModule());
        MAPPER.setAnnotationIntrospector(new AsArrayIntrospector());
    }

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public CBORColumnReadAfterburner() {
        super(SMILES, MAPPER);
    }
}

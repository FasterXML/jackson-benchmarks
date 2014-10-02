package com.fasterxml.jackson.perf.cbor;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Group) // Thread, Group or Benchmark
public class CBORColumnWriteVanilla
    extends WritePerfBasicJackson
{
    private static final ObjectMapper MAPPER;
    static {
        CBORFactory f = new CBORFactory();
    	    // configure differently?
    	    MAPPER = new ObjectMapper(f);
         MAPPER.setAnnotationIntrospector(new AsArrayIntrospector());
    }

    public CBORColumnWriteVanilla() {
        super(MAPPER);
    }
}

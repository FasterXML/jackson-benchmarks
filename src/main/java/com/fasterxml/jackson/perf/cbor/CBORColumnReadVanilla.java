package com.fasterxml.jackson.perf.cbor;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Thread)
public class CBORColumnReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private final static CBORFactory _cf = new CBORFactory();
    
    private static final ObjectMapper MAPPER = new ObjectMapper(_cf);
    static {
        MAPPER.setAnnotationIntrospector(new AsArrayIntrospector());
    }

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public CBORColumnReadVanilla() {
        super(MediaItem.class, SMILES, MAPPER);
    }
}

package com.fasterxml.jackson.perf.cbor;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;

import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.dataformat.cbor.databind.CBORMapper;

import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class CBORStdReadVanilla
    extends ReadPerfBaseFullJackson<MediaItem>
{
    private final static CBORFactory _cf = CBORFactory.builder()
//            .disable(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES)
            .build();
    
    private static final ObjectMapper MAPPER = new CBORMapper(_cf);

    private final static InputConverter CBORS = InputConverter.stdConverter(MAPPER);

    public CBORStdReadVanilla() {
        super(MediaItem.class, CBORS, MAPPER);
    }
}

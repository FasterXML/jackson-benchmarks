package com.fasterxml.jackson.perf.cbor;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;

import tools.jackson.dataformat.cbor.CBORFactory;
import tools.jackson.dataformat.cbor.CBORMapper;

import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Thread)
public class CBORColumnReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private final static CBORFactory _cf = CBORFactory.builder().build();
    private final static ObjectMapper MAPPER = CBORMapper.builder(_cf)
            .annotationIntrospector(new AsArrayIntrospector())
            .build();

    public CBORColumnReadVanilla() {
        super(MediaItem.class, InputConverter.stdConverter(MAPPER), MAPPER);
    }
}

package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Thread)
public class SmileColumnReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = ObjectMapper.builder(new SmileFactory())
            .annotationIntrospector(new AsArrayIntrospector())
            .build();

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public SmileColumnReadVanilla() {
        super(MediaItem.class, SMILES, MAPPER);
    }
}

package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;
import tools.jackson.dataformat.smile.SmileMapper;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Thread)
public class SmileColumnReadAfterburner
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = _withAfterburner(SmileMapper.builder())
            .annotationIntrospector(new AsArrayIntrospector())
            .build();

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public SmileColumnReadAfterburner() {
        super(MediaItem.class, SMILES, MAPPER);
    }
}

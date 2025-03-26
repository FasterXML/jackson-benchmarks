package com.fasterxml.jackson.perf.cbor;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;
import tools.jackson.dataformat.cbor.CBORMapper;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Thread)
public class CBORColumnReadAfterburner
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = _withAfterburner(CBORMapper.builder())
            .annotationIntrospector(new AsArrayIntrospector())
            .build();

    public CBORColumnReadAfterburner() {
        super(MediaItem.class, InputConverter.stdConverter(MAPPER), MAPPER);
    }
}

package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Thread)
public class SmileColumnWriteAfterburner
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = _withAfterburner(PerfDefaultsForSmile.smileMapperBuilder())
            .annotationIntrospector(new AsArrayIntrospector())
            .build();

    public SmileColumnWriteAfterburner() {
        super(MAPPER);
    }
}

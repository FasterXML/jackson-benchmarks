package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;
import tools.jackson.dataformat.smile.SmileMapper;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class SmileStdWriteAfterburner
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = _withAfterburner(SmileMapper.builder())
            .build();

    public SmileStdWriteAfterburner() {
        super(MAPPER);
    }
}

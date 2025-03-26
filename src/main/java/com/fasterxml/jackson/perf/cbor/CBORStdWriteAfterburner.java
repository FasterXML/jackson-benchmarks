package com.fasterxml.jackson.perf.cbor;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;
import tools.jackson.dataformat.cbor.CBORMapper;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class CBORStdWriteAfterburner
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = _withAfterburner(CBORMapper.builder())
            .build();

    public CBORStdWriteAfterburner() {
        super(MAPPER);
    }
}

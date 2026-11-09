package com.fasterxml.jackson.perf.ion;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;

import tools.jackson.dataformat.ion.IonObjectMapper;

import tools.jackson.module.blackbird.BlackbirdModule;

import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class IonStdWriteBlackbird
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = IonObjectMapper.builder()
            .addModule(new BlackbirdModule())
            .build();

    public IonStdWriteBlackbird() {
        super(MAPPER);
    }
}

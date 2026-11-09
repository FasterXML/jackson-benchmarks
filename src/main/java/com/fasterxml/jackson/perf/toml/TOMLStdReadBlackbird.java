package com.fasterxml.jackson.perf.toml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;
import tools.jackson.dataformat.toml.TomlMapper;

import tools.jackson.module.blackbird.BlackbirdModule;

import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class TOMLStdReadBlackbird
// could be full, but let's avoid since extra results not very useful
    extends ReadPerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = TomlMapper.builder()
            .addModule(new BlackbirdModule())
            .build();

    public TOMLStdReadBlackbird() {
        super(MediaItem.class, InputConverter.stdConverter(MAPPER), MAPPER);
    }
}

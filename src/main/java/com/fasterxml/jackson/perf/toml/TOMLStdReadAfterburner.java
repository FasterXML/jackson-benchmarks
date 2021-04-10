package com.fasterxml.jackson.perf.toml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class TOMLStdReadAfterburner
// could be full, but let's avoid since extra results not very useful
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = TomlMapper.builder()
            .addModule(new AfterburnerModule())
            .build();

    public TOMLStdReadAfterburner() {
        super(MediaItem.class, InputConverter.stdConverter(MAPPER), MAPPER);
    }
}

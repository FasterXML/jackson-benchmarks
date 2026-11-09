package com.fasterxml.jackson.perf.toml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;
import tools.jackson.dataformat.toml.TomlMapper;

import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class TOMLStdReadBlackbird
// could be full, but let's avoid since extra results not very useful
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = _withBlackbird(TomlMapper.builder())
            .build();

    public TOMLStdReadBlackbird() {
        super(MediaItem.class, InputConverter.stdConverter(MAPPER), MAPPER);
    }
}

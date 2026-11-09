package com.fasterxml.jackson.perf.toml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.ObjectMapper;

import tools.jackson.dataformat.toml.TomlMapper;

import com.fasterxml.jackson.perf.WritePerfBaseFullJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class TOMLStdWriteBlackbird
    extends WritePerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = _withBlackbird(TomlMapper.builder())
            .build();

    public TOMLStdWriteBlackbird() {
        super(MAPPER);
    }
}

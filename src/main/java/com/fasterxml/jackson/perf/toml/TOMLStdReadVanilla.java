package com.fasterxml.jackson.perf.toml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;

import com.fasterxml.jackson.dataformat.toml.TomlMapper;

import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class TOMLStdReadVanilla
    extends ReadPerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new TomlMapper();

    public TOMLStdReadVanilla() {
        super(MediaItem.class, InputConverter.stdConverter(MAPPER), MAPPER);
    }
}

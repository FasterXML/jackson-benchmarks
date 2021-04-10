package com.fasterxml.jackson.perf.toml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class TOMLStdWriteVanilla
    extends WritePerfBasicJackson<MediaItem>
{
    public TOMLStdWriteVanilla() {
        super(new TomlMapper());
    }
}

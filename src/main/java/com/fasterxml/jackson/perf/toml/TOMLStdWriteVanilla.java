package com.fasterxml.jackson.perf.toml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.dataformat.toml.TomlMapper;

import com.fasterxml.jackson.perf.WritePerfBaseFullJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class TOMLStdWriteVanilla
    extends WritePerfBaseFullJackson<MediaItem>
{
    public TOMLStdWriteVanilla() {
        super(new TomlMapper());
    }
}

package com.fasterxml.jackson.perf.yaml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.perf.WritePerfBaseFullJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Group) // Thread, Group or Benchmark
public class YAMLStdWriteVanilla
    extends WritePerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new YAMLMapper();

    public YAMLStdWriteVanilla() {
        super(MAPPER);
    }
}

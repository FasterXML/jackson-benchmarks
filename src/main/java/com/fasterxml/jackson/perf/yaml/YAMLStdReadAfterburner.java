package com.fasterxml.jackson.perf.yaml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Group) // Thread, Group or Benchmark
public class YAMLStdReadAfterburner
// could be full, but let's avoid since extra results not very useful
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new YAMLMapper()
        .registerModule(new AfterburnerModule());

    public YAMLStdReadAfterburner() {
        super(MediaItem.class, InputConverter.stdConverter(MAPPER), MAPPER);
    }
}

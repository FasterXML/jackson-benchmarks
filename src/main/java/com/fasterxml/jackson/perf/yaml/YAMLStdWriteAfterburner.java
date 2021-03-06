package com.fasterxml.jackson.perf.yaml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class YAMLStdWriteAfterburner
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = YAMLMapper.builder()
            .addModule(new AfterburnerModule())
            .build();

    public YAMLStdWriteAfterburner() {
        super(MAPPER);
    }
}

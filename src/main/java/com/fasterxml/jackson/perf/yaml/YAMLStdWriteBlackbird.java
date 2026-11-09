package com.fasterxml.jackson.perf.yaml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.yaml.YAMLMapper;
import tools.jackson.module.blackbird.BlackbirdModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class YAMLStdWriteBlackbird
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = YAMLMapper.builder()
            .addModule(new BlackbirdModule())
            .build();

    public YAMLStdWriteBlackbird() {
        super(MAPPER);
    }
}

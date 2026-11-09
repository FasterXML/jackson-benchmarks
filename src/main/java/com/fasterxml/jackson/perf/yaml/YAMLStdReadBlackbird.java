package com.fasterxml.jackson.perf.yaml;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;
import tools.jackson.dataformat.yaml.YAMLMapper;
import tools.jackson.module.blackbird.BlackbirdModule;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class YAMLStdReadBlackbird
// could be full, but let's avoid since extra results not very useful
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = YAMLMapper.builder()
            .addModule(new BlackbirdModule())
            .build();

    public YAMLStdReadBlackbird() {
        super(MediaItem.class, InputConverter.stdConverter(MAPPER), MAPPER);
    }
}

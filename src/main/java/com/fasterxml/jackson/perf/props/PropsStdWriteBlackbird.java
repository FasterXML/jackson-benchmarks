package com.fasterxml.jackson.perf.props;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.dataformat.javaprop.JavaPropsMapper;
import tools.jackson.module.blackbird.BlackbirdModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class PropsStdWriteBlackbird
    extends WritePerfBasicJackson<MediaItem>
{
    private static final JavaPropsMapper MAPPER = JavaPropsMapper.builder()
            .addModule(new BlackbirdModule())
            .build();

    public PropsStdWriteBlackbird() {
        super(MAPPER);
    }
}

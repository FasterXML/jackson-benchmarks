package com.fasterxml.jackson.perf.props;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class PropsStdWriteBlackbird
    extends WritePerfBasicJackson<MediaItem>
{
    private static final JavaPropsMapper MAPPER = _withBlackbird(JavaPropsMapper.builder())
            .build();

    public PropsStdWriteBlackbird() {
        super(MAPPER);
    }
}

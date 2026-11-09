package com.fasterxml.jackson.perf.props;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.dataformat.javaprop.JavaPropsMapper;
import tools.jackson.module.blackbird.BlackbirdModule;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class PropsStdReadBlackbird
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final JavaPropsMapper MAPPER = JavaPropsMapper.builder()
            .addModule(new BlackbirdModule())
            .build();

    private final static InputConverter PROPS = InputConverter.stdConverter(MAPPER);

    public PropsStdReadBlackbird() {
        super(MediaItem.class, PROPS, MAPPER);
    }
}

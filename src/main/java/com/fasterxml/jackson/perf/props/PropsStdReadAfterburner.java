package com.fasterxml.jackson.perf.props;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class PropsStdReadAfterburner
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final JavaPropsMapper MAPPER = new JavaPropsMapper();
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

    private final static InputConverter PROPS = InputConverter.stdConverter(MAPPER);

    public PropsStdReadAfterburner() {
        super(MediaItem.class, PROPS, MAPPER);
    }
}

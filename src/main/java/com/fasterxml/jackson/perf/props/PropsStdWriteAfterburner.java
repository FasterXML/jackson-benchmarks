package com.fasterxml.jackson.perf.props;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class PropsStdWriteAfterburner
    extends WritePerfBasicJackson<MediaItem>
{
    private static final JavaPropsMapper PROPS_MAPPER = new JavaPropsMapper();
    static {
        PROPS_MAPPER.registerModule(new AfterburnerModule());
    }

    public PropsStdWriteAfterburner() {
        super(PROPS_MAPPER);
    }
}

package com.fasterxml.jackson.perf.javaprops;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class PropertiesStdWriteAfterburner
    extends WritePerfBasicJackson<MediaItem>
{
    private static final JavaPropsMapper PROPS_MAPPER = new JavaPropsMapper();
    static {
        PROPS_MAPPER.registerModule(new AfterburnerModule());
    }

    public PropertiesStdWriteAfterburner() {
        super(PROPS_MAPPER);
    }
}

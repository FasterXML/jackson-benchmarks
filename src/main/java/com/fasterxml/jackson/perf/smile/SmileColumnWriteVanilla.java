package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Thread)
public class SmileColumnWriteVanilla
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER;
    static {
        SmileFactory f = new SmileFactory();
        // configure differently?
        MAPPER = new ObjectMapper(f);
        MAPPER.setAnnotationIntrospector(new AsArrayIntrospector());
        MAPPER.registerModule(new AfterburnerModule());
    }

    public SmileColumnWriteVanilla() {
        super(MAPPER);
    }
}

package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.perf.WritePerfBaseFullJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class SmileStdWriteVanilla
    extends WritePerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper SMILE_MAPPER;
    static {
        // configure differently?
        SMILE_MAPPER = new ObjectMapper(new SmileFactory());
    }

    public SmileStdWriteVanilla() {
        super(SMILE_MAPPER);
    }
}

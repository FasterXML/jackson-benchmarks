package com.fasterxml.jackson.perf.ion;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.ion.IonFactory;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class IonStdWriteVanilla
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper FORMAT_MAPPER;
    static {
        // configure differently?
        FORMAT_MAPPER = new ObjectMapper(new IonFactory());
    }

    public IonStdWriteVanilla() {
        super(FORMAT_MAPPER);
    }
}

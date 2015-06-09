package com.fasterxml.jackson.perf.cbor;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.perf.WritePerfBaseFullJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class CBORStdWriteVanilla
    extends WritePerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper CBOR_MAPPER;
    static {
        // configure differently?
        CBOR_MAPPER = new ObjectMapper(new CBORFactory());
    }

    public CBORStdWriteVanilla() {
        super(CBOR_MAPPER);
    }
}

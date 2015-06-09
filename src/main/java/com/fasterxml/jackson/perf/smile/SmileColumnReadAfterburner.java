package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.util.AsArrayIntrospector;

@State(Scope.Thread)
public class SmileColumnReadAfterburner
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private final static SmileFactory _sf = new SmileFactory();
    
    private static final ObjectMapper MAPPER = new ObjectMapper(_sf);
    static {
        MAPPER.registerModule(new AfterburnerModule());
        MAPPER.setAnnotationIntrospector(new AsArrayIntrospector());
    }

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public SmileColumnReadAfterburner() {
        super(MediaItem.class, SMILES, MAPPER);
    }
}

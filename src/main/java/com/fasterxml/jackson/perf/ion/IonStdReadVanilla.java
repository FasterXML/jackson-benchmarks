package com.fasterxml.jackson.perf.ion;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.ion.IonFactory;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class IonStdReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private final static IonFactory _cf = new IonFactory();
    
    private static final ObjectMapper MAPPER = new ObjectMapper(_cf);

    private final static InputConverter CBORS = InputConverter.stdConverter(MAPPER);

    public IonStdReadVanilla() {
        super(MediaItem.class, CBORS, MAPPER);
    }
}

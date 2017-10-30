package com.fasterxml.jackson.perf.ion;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.ion.IonFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class IonStdReadAfterburner
//29-Oct-2017, tatu: Let's limit to basic set; technically easy to extend by uncommenting:
//extends ReadPerfBaseFullJackson<MediaItem>
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new ObjectMapper(new IonFactory());
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public IonStdReadAfterburner() {
        super(MediaItem.class, SMILES, MAPPER);
    }
}

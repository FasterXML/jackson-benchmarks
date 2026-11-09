package com.fasterxml.jackson.perf.ion;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;

import tools.jackson.dataformat.ion.IonObjectMapper;

import tools.jackson.module.blackbird.BlackbirdModule;

import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class IonStdReadBlackbird
//29-Oct-2017, tatu: Let's limit to basic set; technically easy to extend by uncommenting:
//extends ReadPerfBaseFullJackson<MediaItem>
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = IonObjectMapper.builder()
            .addModule(new BlackbirdModule())
            .build();

    public IonStdReadBlackbird() {
        super(MediaItem.class, InputConverter.stdConverter(MAPPER), MAPPER);
    }
}

package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class SmileStdReadBlackbird
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = _withBlackbird(
            PerfDefaultsForSmile.smileMapperBuilder())
        .build();

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public SmileStdReadBlackbird() {
        super(MediaItem.class, SMILES, MAPPER);
    }
}

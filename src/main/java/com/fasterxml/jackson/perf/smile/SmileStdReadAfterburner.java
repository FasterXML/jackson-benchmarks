package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;
import tools.jackson.dataformat.smile.SmileMapper;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class SmileStdReadAfterburner
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = _withAfterburner(SmileMapper.builder())
            .build();

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public SmileStdReadAfterburner() {
        super(MediaItem.class, SMILES, MAPPER);
    }
}

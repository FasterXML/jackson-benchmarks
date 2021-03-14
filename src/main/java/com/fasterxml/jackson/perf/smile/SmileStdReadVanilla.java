package com.fasterxml.jackson.perf.smile;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;

import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.dataformat.smile.databind.SmileMapper;

import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class SmileStdReadVanilla
    extends ReadPerfBaseFullJackson<MediaItem>
{
    private final static SmileFactory _sf = SmileFactory.builder()
//            .disable(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES)
            .build();

    private static final ObjectMapper MAPPER = new SmileMapper(_sf);

    private final static InputConverter SMILES = InputConverter.stdConverter(MAPPER);

    public SmileStdReadVanilla() {
        super(MediaItem.class, SMILES, MAPPER);
    }
}

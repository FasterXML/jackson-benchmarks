package com.fasterxml.jackson.perf.bson;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;

import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class BSONStdReadAfterburner
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper BSON_MAPPER = _withAfterburner(
            PerfDefaultsForBSON.bsonMapperBuilder())
        .build();

    private final static InputConverter INPUTS = InputConverter.stdConverter(BSON_MAPPER);

    public BSONStdReadAfterburner() {
        super(MediaItem.class, INPUTS, BSON_MAPPER);
    }
}

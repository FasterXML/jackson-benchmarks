package com.fasterxml.jackson.perf.bson;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;

import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class BSONStdWriteBlackbird
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper BSON_MAPPER = _withBlackbird(
            PerfDefaultsForBSON.bsonMapperBuilder())
        .build();

    public BSONStdWriteBlackbird() {
        super(BSON_MAPPER);
    }
}

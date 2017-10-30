package com.fasterxml.jackson.perf.bson;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

import de.undercouch.bson4jackson.BsonFactory;
import de.undercouch.bson4jackson.BsonModule;

@State(Scope.Thread)
public class BSONStdWriteVanilla
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper BSON_MAPPER = new ObjectMapper(new BsonFactory())
        .registerModule(new BsonModule());

    public BSONStdWriteVanilla() {
        super(BSON_MAPPER);
    }
}

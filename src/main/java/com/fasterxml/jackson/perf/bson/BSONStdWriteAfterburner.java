package com.fasterxml.jackson.perf.bson;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.WritePerfBaseFullJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

import de.undercouch.bson4jackson.BsonFactory;
import de.undercouch.bson4jackson.BsonModule;

@State(Scope.Thread)
public class BSONStdWriteAfterburner
    extends WritePerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper BSON_MAPPER = new ObjectMapper(new BsonFactory())
        .registerModule(new BsonModule())
        .registerModule(new AfterburnerModule());

    public BSONStdWriteAfterburner() {
        super(BSON_MAPPER);
    }
}

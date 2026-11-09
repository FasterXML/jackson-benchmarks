package com.fasterxml.jackson.perf.bson;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class BSONStdWriteVanilla
    extends WritePerfBasicJackson<MediaItem>
{
    public BSONStdWriteVanilla() {
        super(PerfDefaultsForBSON.bsonMapper());
    }
}

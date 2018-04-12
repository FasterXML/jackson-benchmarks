package com.fasterxml.jackson.perf.protob;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;

import com.fasterxml.jackson.dataformat.protobuf.ProtobufMapper;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;

import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class ProtobufStdWriteAfterburner
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = ProtobufMapper.builder()
            .addModule(new AfterburnerModule())
            .build();

    private final static ProtobufSchema _mediaItemSchema = ProtobufHelper.mediaItemSchema();

    public ProtobufStdWriteAfterburner() {
        super(MAPPER, _mediaItemSchema);
    }
}

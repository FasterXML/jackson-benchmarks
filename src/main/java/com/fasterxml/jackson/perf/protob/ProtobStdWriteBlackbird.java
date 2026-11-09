package com.fasterxml.jackson.perf.protob;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.databind.*;

import tools.jackson.dataformat.protobuf.ProtobufMapper;
import tools.jackson.dataformat.protobuf.schema.ProtobufSchema;

import tools.jackson.module.blackbird.BlackbirdModule;

import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class ProtobStdWriteBlackbird
    extends WritePerfBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = ProtobufMapper.builder()
            .addModule(new BlackbirdModule())
            .build();

    private final static ProtobufSchema _mediaItemSchema = ProtobufHelper.mediaItemSchema();

    public ProtobStdWriteBlackbird() {
        super(MAPPER, _mediaItemSchema);
    }
}

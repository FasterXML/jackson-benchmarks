package com.fasterxml.jackson.perf.manual;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.protobuf.ProtobufFactory;
import tools.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.fasterxml.jackson.perf.model.MediaItems;
import com.fasterxml.jackson.perf.protob.ProtobufHelper;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class ProtobufManualWriteVanilla
    extends ManualWritePerfTest
{
    private static final ObjectMapper MAPPER = new ObjectMapper(new ProtobufFactory());

    private final static ProtobufSchema _mediaItemSchema = ProtobufHelper.mediaItemSchema();

    public ProtobufManualWriteVanilla() {
        super(MAPPER.writer(_mediaItemSchema), MediaItems.stdMediaItem());
    }
}

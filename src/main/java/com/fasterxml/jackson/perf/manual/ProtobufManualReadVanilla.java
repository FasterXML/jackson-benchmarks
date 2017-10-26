package com.fasterxml.jackson.perf.manual;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufFactory;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;
import com.fasterxml.jackson.perf.protob.ProtobufHelper;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class ProtobufManualReadVanilla
    extends ManualReadPerfTest
{
    private static final ObjectMapper MAPPER = new ObjectMapper(new ProtobufFactory());

    private final static ProtobufSchema _mediaItemSchema = ProtobufHelper.mediaItemSchema();

    private final static MinimalInputConverter CONV = MinimalInputConverter.minimalConverter(MAPPER, _mediaItemSchema);

    public ProtobufManualReadVanilla() {
        super(CONV, MAPPER.reader(_mediaItemSchema));
    }
}

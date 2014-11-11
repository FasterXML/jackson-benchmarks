package com.fasterxml.jackson.perf.protobuf;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufFactory;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;

@State(Scope.Group) // Thread, Group or Benchmark
@OutputTimeUnit(TimeUnit.SECONDS)
public class ProtobufStdReadAfterburner
    extends ReadPerfBaseBasicJackson
{
    private static final ObjectMapper MAPPER = new ObjectMapper(new ProtobufFactory());
    static {
        MAPPER.registerModule(new AfterburnerModule());
    }

    private final static ProtobufSchema _mediaItemSchema = ProtobufHelper.mediaItemSchema();
    private final static MinimalInputConverter CONV = MinimalInputConverter.minimalConverter(MAPPER, _mediaItemSchema);

    public ProtobufStdReadAfterburner() {
        super(CONV, MAPPER);
    }
}

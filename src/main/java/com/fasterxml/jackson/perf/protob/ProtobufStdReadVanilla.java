package com.fasterxml.jackson.perf.protob;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufFactory;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class ProtobufStdReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new ObjectMapper(new ProtobufFactory());

    private final static ProtobufSchema _mediaItemSchema = ProtobufHelper.mediaItemSchema();

    private final static MinimalInputConverter CONV = MinimalInputConverter.minimalConverter(MAPPER, _mediaItemSchema);

    public ProtobufStdReadVanilla() {
        super(MediaItem.class, CONV, MAPPER, _mediaItemSchema);
    }
}

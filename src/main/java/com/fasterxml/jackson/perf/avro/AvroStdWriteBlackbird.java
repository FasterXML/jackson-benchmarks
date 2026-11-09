package com.fasterxml.jackson.perf.avro;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.dataformat.avro.AvroMapper;
import tools.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class AvroStdWriteBlackbird
    extends WritePerfBasicJackson<MediaItem>
{
    private static final AvroMapper MAPPER = (AvroMapper) _withBlackbird(AvroMapper.builder())
            .build();

    private final static AvroSchema _mediaItemSchema;
    static {
         try {
             _mediaItemSchema = MAPPER.schemaFor(MediaItem.class);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }

    public AvroStdWriteBlackbird() {
        super(MAPPER, _mediaItemSchema);
    }
}

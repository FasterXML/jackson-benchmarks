package com.fasterxml.jackson.perf.avro;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.dataformat.avro.*;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class AvroStdWriteVanilla
    extends WritePerfBasicJackson<MediaItem>
{
    private static final AvroMapper MAPPER = new AvroMapper();

    private final static AvroSchema _mediaItemSchema;
    static {
         try {
             _mediaItemSchema = MAPPER.schemaFor(MediaItem.class);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }

    public AvroStdWriteVanilla() {
        super(MAPPER, _mediaItemSchema);
    }
}

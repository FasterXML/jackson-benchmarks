package com.fasterxml.jackson.perf.avro;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.dataformat.avro.*;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class AvroStdReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
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

    private final static MinimalInputConverter AVROS = MinimalInputConverter.minimalConverter(MAPPER, _mediaItemSchema);

    public AvroStdReadVanilla() {
        super(MediaItem.class, AVROS, MAPPER, _mediaItemSchema);
    }
}

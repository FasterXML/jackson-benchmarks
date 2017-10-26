package com.fasterxml.jackson.perf.manual;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class AvroManualWriteVanilla
    extends ManualWritePerfTest
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

    public AvroManualWriteVanilla() {
        super(MAPPER.writer(_mediaItemSchema), MediaItems.stdMediaItem());
    }
}

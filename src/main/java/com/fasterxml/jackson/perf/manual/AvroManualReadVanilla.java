package com.fasterxml.jackson.perf.manual;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import tools.jackson.dataformat.avro.AvroMapper;
import tools.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class AvroManualReadVanilla
    extends ManualReadPerfTest
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

    private final static MinimalInputConverter CONV = MinimalInputConverter.minimalConverter(MAPPER, _mediaItemSchema);
    
    public AvroManualReadVanilla() {
        super(CONV, MAPPER.reader(_mediaItemSchema));
    }
}

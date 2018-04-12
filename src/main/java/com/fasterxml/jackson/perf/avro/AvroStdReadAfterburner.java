package com.fasterxml.jackson.perf.avro;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.dataformat.avro.*;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

@State(Scope.Thread)
public class AvroStdReadAfterburner
    extends ReadPerfBaseBasicJackson<MediaItem>
{
    private static final AvroMapper MAPPER = (AvroMapper) _withAfterburner(AvroMapper.builder())
            .build();

    private final static AvroSchema _mediaItemSchema;
    static {
	    try {
	        _mediaItemSchema = MAPPER.schemaFor(MediaItem.class);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
    }

    private final static MinimalInputConverter AVROS = MinimalInputConverter.minimalConverter(MAPPER, _mediaItemSchema);

    public AvroStdReadAfterburner() {
        super(MediaItem.class, AVROS, MAPPER, _mediaItemSchema);
    }
}

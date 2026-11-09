package com.fasterxml.jackson.perf.csv;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.dataformat.csv.*;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;
import com.fasterxml.jackson.perf.model.FlattenedMediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

@State(Scope.Thread)
public class CsvStdReadBlackbird
    extends ReadPerfBaseBasicJackson<FlattenedMediaItem>
{
    private static final CsvMapper MAPPER = _withBlackbird(CsvMapper.builder())
            .build();

    private final static CsvSchema _mediaItemSchema;
    static {
        _mediaItemSchema = MAPPER.typedSchemaFor(FlattenedMediaItem.class);
    }

    private final static MinimalInputConverter CONV = MinimalInputConverter.minimalConverter(MAPPER,
            _mediaItemSchema, MediaItems.flatMediaItem());

    public CsvStdReadBlackbird() {
        super(FlattenedMediaItem.class, CONV, MAPPER, _mediaItemSchema);
    }
}

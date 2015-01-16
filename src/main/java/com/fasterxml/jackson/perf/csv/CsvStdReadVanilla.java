package com.fasterxml.jackson.perf.csv;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.dataformat.csv.*;
import com.fasterxml.jackson.perf.ReadPerfBaseBasicJackson;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;
import com.fasterxml.jackson.perf.model.FlattenedMediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

@State(Scope.Group) // Thread, Group or Benchmark
public class CsvStdReadVanilla
    extends ReadPerfBaseBasicJackson<FlattenedMediaItem>
{
    private static final CsvMapper MAPPER = new CsvMapper();
    private final static CsvSchema _mediaItemSchema;
    static {
        _mediaItemSchema = MAPPER.typedSchemaFor(FlattenedMediaItem.class);
    }

    private final static MinimalInputConverter CONV = MinimalInputConverter.minimalConverter(MAPPER,
            _mediaItemSchema, MediaItems.flatMediaItem());

    public CsvStdReadVanilla() {
        super(FlattenedMediaItem.class, CONV, MAPPER, _mediaItemSchema);
    }
}

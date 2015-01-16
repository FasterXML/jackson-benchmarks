package com.fasterxml.jackson.perf.csv;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.FlattenedMediaItem;

@State(Scope.Group) // Thread, Group or Benchmark
public class CsvStdWriteVanilla
    extends WritePerfBasicJackson
{
    private static final CsvMapper MAPPER = new CsvMapper();
    private final static CsvSchema _mediaItemSchema;
    static {
        _mediaItemSchema = MAPPER.typedSchemaFor(FlattenedMediaItem.class);
    }

    public CsvStdWriteVanilla() {
        super(MAPPER, _mediaItemSchema);
    }
}

package com.fasterxml.jackson.perf.csv;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.FlattenedMediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

@State(Scope.Thread)
public class CsvStdWriteVanilla
    extends WritePerfBasicJackson<FlattenedMediaItem>
{
    private static final CsvMapper MAPPER = CsvMapper.builder()
//            .enable(CsvGenerator.Feature.STRICT_CHECK_FOR_QUOTING)
            .build();
    private final static CsvSchema _mediaItemSchema;
    static {
        _mediaItemSchema = MAPPER.typedSchemaFor(FlattenedMediaItem.class);
    }

    public CsvStdWriteVanilla() {
        super(MAPPER, _mediaItemSchema, MediaItems.flatMediaItem());
    }
}

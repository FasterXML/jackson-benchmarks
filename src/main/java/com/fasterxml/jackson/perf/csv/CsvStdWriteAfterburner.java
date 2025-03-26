package com.fasterxml.jackson.perf.csv;

import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import tools.jackson.dataformat.csv.*;
import tools.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.WritePerfBasicJackson;
import com.fasterxml.jackson.perf.model.FlattenedMediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

@State(Scope.Thread)
public class CsvStdWriteAfterburner
    extends WritePerfBasicJackson<FlattenedMediaItem>
{
    private static final CsvMapper MAPPER = CsvMapper.builder()
            .addModule(new AfterburnerModule())
            // 24-Nov-2020, not sure which way to go here, but 2.12 has this:
            .disable(CsvWriteFeature.STRICT_CHECK_FOR_QUOTING)
            .build();

    private final static CsvSchema _mediaItemSchema;
    static {
        _mediaItemSchema = MAPPER.typedSchemaFor(FlattenedMediaItem.class);
    }

    public CsvStdWriteAfterburner() {
        super(MAPPER, _mediaItemSchema, MediaItems.flatMediaItem());
    }
}

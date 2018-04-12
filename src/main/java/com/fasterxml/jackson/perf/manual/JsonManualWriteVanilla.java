package com.fasterxml.jackson.perf.manual;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.fasterxml.jackson.core.json.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.model.MediaItems;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonManualWriteVanilla
    extends ManualWritePerfTest
{
    private static final ObjectMapper MAPPER = new ObjectMapper(new JsonFactory());

    public JsonManualWriteVanilla() {
        super(MAPPER.writer(), MediaItems.stdMediaItem());
    }
}

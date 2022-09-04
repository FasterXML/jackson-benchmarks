package com.fasterxml.jackson.perf.manual;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.perf.model.MediaItems;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class SmileManualWriteVanilla
    extends ManualWritePerfTest
{
    private static final ObjectMapper MAPPER = new ObjectMapper(new SmileFactory());

    public SmileManualWriteVanilla() {
        super(MAPPER.writer(), MediaItems.stdMediaItem());
    }
}

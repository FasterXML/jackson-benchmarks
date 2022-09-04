package com.fasterxml.jackson.perf.manual;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import tools.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.model.MediaItems;
import com.fasterxml.jackson.perf.xml.StaxProvider;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class XMLManualWriteVanilla
    extends ManualWritePerfTest
{
    private static final ObjectMapper MAPPER = StaxProvider.xmlMapper();

    public XMLManualWriteVanilla() {
        super(MAPPER.writer(), MediaItems.stdMediaItem());
    }
}

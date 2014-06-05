package com.fasterxml.jackson.perf;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.data.InputData;

public abstract class ReadPerfBaseFullJackson
	extends ReadPerfBaseBasicJackson
	implements ReadPerfTestFull
{
    protected final ObjectReader UNTYPED_READER;

    protected ReadPerfBaseFullJackson(InputConverter conv, ObjectMapper mapper)
    {
        super(conv, mapper);
        UNTYPED_READER = mapper.reader(Object.class);
    }
    
    /*
    /**********************************************************************
    /* Untyped reading tests
    /**********************************************************************
     */
    
    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readTreeCitmCatalog(BlackHole bh) throws Exception {
        bh.consume(read(CONV.bytes(InputData.CITM_CATALOG_WS), UNTYPED_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readTreeWebxml(BlackHole bh) throws Exception {
        bh.consume(read(CONV.bytes(InputData.WEBXML_WS), UNTYPED_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readTreeMenu(BlackHole bh) throws Exception {
        bh.consume(read(CONV.bytes(InputData.MENU_WS), UNTYPED_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readTreeMediaItem(BlackHole bh) throws Exception {
        bh.consume(read(CONV.bytesForMediaItem(), UNTYPED_READER));
    }
}

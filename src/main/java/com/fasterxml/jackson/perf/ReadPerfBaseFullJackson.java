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
	protected final InputConverter FULL_CONVERTER;
	
    protected final ObjectReader UNTYPED_READER;

    protected ReadPerfBaseFullJackson(InputConverter conv, ObjectMapper mapper)
    {
        super(conv, mapper);
        FULL_CONVERTER = conv;
        UNTYPED_READER = mapper.reader(Object.class);
    }
    
    /*
    /**********************************************************************
    /* Untyped reading tests
    /**********************************************************************
     */
    
    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readTreeCitmCatalog(BlackHole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.CITM_CATALOG_WS), UNTYPED_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readTreeWebxml(BlackHole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.WEBXML_WS), UNTYPED_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readTreeMenu(BlackHole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.MENU_WS), UNTYPED_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readTreeMediaItem(BlackHole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytesForMediaItem(), UNTYPED_READER));
    }
}

package com.fasterxml.jackson.perf;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.data.InputData;

public abstract class ReadPerfBaseFullJackson<T>
	extends ReadPerfBaseBasicJackson<T>
	implements ReadPerfTestFull
{
    protected final InputConverter FULL_CONVERTER;

    protected final ObjectReader UNTYPED_READER;

    protected final ObjectReader NODE_READER;
    
    protected ReadPerfBaseFullJackson(Class<T> type, InputConverter conv, ObjectMapper mapper)
    {
        super(type, conv, mapper);
        FULL_CONVERTER = conv;
        UNTYPED_READER = mapper.reader(Object.class);
        NODE_READER = mapper.reader(JsonNode.class);
    }

    /*
    /**********************************************************************
    /* Untyped ("map") reading tests
    /**********************************************************************
     */

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedCitmCatalog(BlackHole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.CITM_CATALOG_WS), UNTYPED_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedWebxml(BlackHole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.WEBXML_WS), UNTYPED_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedMenu(BlackHole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.MENU_WS), UNTYPED_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedMediaItem(BlackHole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytesForMediaItem(), UNTYPED_READER));
    }

    /*
    /**********************************************************************
    /* JsonNode reading tests
    /**********************************************************************
     */

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeCitmCatalog(BlackHole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.CITM_CATALOG_WS), NODE_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeWebxml(BlackHole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.WEBXML_WS), NODE_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeMenu(BlackHole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.MENU_WS), NODE_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeMediaItem(BlackHole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytesForMediaItem(), NODE_READER));
    }
}

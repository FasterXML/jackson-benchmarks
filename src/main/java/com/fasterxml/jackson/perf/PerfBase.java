package com.fasterxml.jackson.perf;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.data.InputData;
import com.fasterxml.jackson.perf.model.MediaItem;

public abstract class PerfBase
{
    protected final InputConverter CONV;

    protected final ObjectReader UNTYPED_READER;

    protected final ObjectReader MEDIA_ITEM_READER;
    
    protected PerfBase(InputConverter conv, ObjectMapper mapper)
    {
        CONV = conv;

        UNTYPED_READER = mapper.reader(Object.class);
        MEDIA_ITEM_READER = mapper.reader(MediaItem.class);
    }

    /*
    /**********************************************************************
    /* Untyped reading tests
    /**********************************************************************
     */
    
    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readCitmCatalog(BlackHole bh) throws Exception {
        bh.consume(readUntyped(CONV.bytes(InputData.CITM_CATALOG_WS)));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readWebxml(BlackHole bh) throws Exception {
        bh.consume(readUntyped(CONV.bytes(InputData.WEBXML_WS)));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readMenu(BlackHole bh) throws Exception {
        bh.consume(readUntyped(CONV.bytes(InputData.MENU_WS)));
    }

    /*
    /**********************************************************************
    /* Typed reading tests
    /**********************************************************************
     */

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readMediaItem(BlackHole bh) throws Exception {
        bh.consume(readUntyped(CONV.bytesForMediaItem()));
    }

    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    private Object readUntyped(byte[] input) throws IOException {
        return UNTYPED_READER.readValue(input);
    }
}

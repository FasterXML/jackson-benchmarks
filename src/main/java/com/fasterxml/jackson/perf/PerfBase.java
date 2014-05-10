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
    
    /*
    /**********************************************************************
    /* Typed reading tests
    /**********************************************************************
     */

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readPojoMediaItem(BlackHole bh) throws Exception {
        bh.consume(read(CONV.bytesForMediaItem(), MEDIA_ITEM_READER));
    }

    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    private final Object read(byte[] input, ObjectReader reader) throws IOException {
        return reader.readValue(input);
    }
}

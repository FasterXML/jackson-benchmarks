package com.fasterxml.jackson.perf;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

public abstract class ReadPerfBaseBasicJackson
	implements ReadPerfTestBasic
{
    protected final InputConverter CONV;

    protected final ObjectReader MEDIA_ITEM_READER;

    protected ReadPerfBaseBasicJackson(InputConverter conv, ObjectMapper mapper)
    {
        CONV = conv;
        MEDIA_ITEM_READER = mapper.reader(MediaItem.class);
    }
    
    /*
    /**********************************************************************
    /* Typed reading tests
    /**********************************************************************
     */

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readPojoMediaItem(BlackHole bh) throws Exception {
        bh.consume(read(CONV.bytesForMediaItem(), MEDIA_ITEM_READER));
    }

    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    protected final Object read(byte[] input, ObjectReader reader) throws IOException {
        return reader.readValue(input);
    }
}

package com.fasterxml.jackson.perf;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;

public abstract class ReadPerfBaseBasicJackson<T>
	implements ReadPerfTestBasic
{
    protected final MinimalInputConverter MINIMAL_CONV;

    protected final ObjectReader MEDIA_ITEM_READER;

    protected ReadPerfBaseBasicJackson(Class<T> type,
            MinimalInputConverter conv, ObjectMapper mapper) {
        this(type, conv, mapper, null);
    }

    protected ReadPerfBaseBasicJackson(Class<T> type,
            MinimalInputConverter conv, ObjectMapper mapper, FormatSchema schema)
    {
        MINIMAL_CONV = conv;
        ObjectReader r = mapper.readerFor(type);
        if (schema != null) {
            r = r.with(schema);
        }
        MEDIA_ITEM_READER = r;
    }

    /*
    /**********************************************************************
    /* Typed reading tests
    /**********************************************************************
     */

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readPojoMediaItem(Blackhole bh) throws Exception {
        bh.consume(read(MINIMAL_CONV.mediaItemAsBytes(), MEDIA_ITEM_READER));
    }

    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    protected Object read(byte[] input, ObjectReader reader) throws IOException {
        return reader.readValue(input);
    }
}

package com.fasterxml.jackson.perf.manual;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.perf.ReadPerfTestBasic;
import com.fasterxml.jackson.perf.data.MinimalInputConverter;

/**
 * Shared base class for manual reading -- that is, use of hand-written deserializer --
 * for all dataformats.
 */
public abstract class ManualReadPerfTest
    implements ReadPerfTestBasic
{
    /**
     * Converter for converting input data
     */
    protected final MinimalInputConverter MINIMAL_CONV;

    protected final MediaItemCodec _codec = new MediaItemCodec();

    /**
     * Properly configured instance that uses expected <code>TokenStreamFactory</code>
     * as well as <code>FormatSchema</code>
     */
    protected final ObjectReader _reader;

    protected ManualReadPerfTest(MinimalInputConverter conv, ObjectReader r) {
        MINIMAL_CONV = conv;
        _reader = r;
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
        bh.consume(read(MINIMAL_CONV.mediaItemAsBytes(), _reader));
    }

    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    protected Object read(byte[] input, ObjectReader reader) throws IOException {
        try (JsonParser p = _reader.createParser(input)) {
            return _codec.deserialize(p);
        }
    }
}

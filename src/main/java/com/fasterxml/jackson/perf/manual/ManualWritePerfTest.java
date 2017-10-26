package com.fasterxml.jackson.perf.manual;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.perf.WritePerfTestBasic;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.util.NopOutputStream;

/**
 * Shared base class for manual writing -- that is, use of hand-written serializer --
 * for all dataformats.
 */
public abstract class ManualWritePerfTest
    implements WritePerfTestBasic
{
    protected final MediaItemCodec _codec = new MediaItemCodec();

    /**
     * Properly configured instance that uses expected <code>TokenStreamFactory</code>
     * as well as <code>FormatSchema</code>
     */
    protected final ObjectWriter _writer;

    protected final MediaItem _item;

    protected ManualWritePerfTest(ObjectWriter w, MediaItem v) {
        _writer = w;
        _item = v;
    }

    /*
    /**********************************************************************
    /* Typed reading tests
    /**********************************************************************
     */

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void writePojoMediaItem(Blackhole bh) throws Exception {
        bh.consume(write(_writer, _item));
    }

    /*
    /**********************************************************************
    /* Helper methods
    /**********************************************************************
     */

    @SuppressWarnings("resource")
    protected final int write(ObjectWriter w, MediaItem value) throws IOException {
        NopOutputStream out = new NopOutputStream();
        try (JsonGenerator g = _writer.createGenerator(out)) {
            _codec.serialize(g, value);
            return out.size();
        }
    }
}

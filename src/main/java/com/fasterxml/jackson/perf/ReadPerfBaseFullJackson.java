package com.fasterxml.jackson.perf;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.core.FormatSchema;
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
        UNTYPED_READER = mapper.readerFor(Object.class);
        NODE_READER = mapper.readerFor(JsonNode.class);
    }

    protected ReadPerfBaseFullJackson(Class<T> type, InputConverter conv,
            ObjectMapper mapper, FormatSchema schema)
    {
        super(type, conv, mapper);
        FULL_CONVERTER = conv;
        ObjectReader r;
        
        r = mapper.readerFor(Object.class);
        if (schema != null) {
            r = r.with(schema);
        }
        UNTYPED_READER = r;
        r = mapper.readerFor(JsonNode.class);
        if (schema != null) {
            r = r.with(schema);
        }
        NODE_READER = r;
    }
    
    /*
    /**********************************************************************
    /* Untyped ("map") reading tests
    /**********************************************************************
     */

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedCitmCatalog(Blackhole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.CITM_CATALOG_WS), UNTYPED_READER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedWebxml(Blackhole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.WEBXML_WS), UNTYPED_READER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedMenu(Blackhole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.MENU_WS), UNTYPED_READER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedMediaItem(Blackhole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.mediaItemAsBytes(), UNTYPED_READER));
    }

    /*
    /**********************************************************************
    /* JsonNode reading tests
    /**********************************************************************
     */

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeCitmCatalog(Blackhole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.CITM_CATALOG_WS), NODE_READER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeWebxml(Blackhole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.WEBXML_WS), NODE_READER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeMenu(Blackhole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.MENU_WS), NODE_READER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeMediaItem(Blackhole bh) throws Exception {
        bh.consume(read(FULL_CONVERTER.mediaItemAsBytes(), NODE_READER));
    }
}

package com.fasterxml.jackson.perf;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.data.InputData;
import com.fasterxml.jackson.perf.model.Currency;
import com.fasterxml.jackson.perf.model.CurrencyBigDecimal;

public abstract class ReadPerfBaseFullJackson<T>
	extends ReadPerfBaseBasicJackson<T>
	implements ReadPerfTestFull
{
    protected final InputConverter FULL_CONVERTER;

    protected final ObjectReader UNTYPED_READER;
    protected final ObjectReader NODE_READER;

    protected final ObjectReader CURRENCY_READER_DEFAULT;
    protected final ObjectReader CURRENCY_READER_FAST;

    protected final ObjectReader CURRENCY_BIGDEC_READER_DEFAULT;
    protected final ObjectReader CURRENCY_BIGDEC_READER_FAST;

    protected ReadPerfBaseFullJackson(Class<T> type, InputConverter conv, ObjectMapper mapper)
    {
        this(type, conv, mapper, null);
        
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

        r = mapper.readerFor(Currency.class);
        // This is unlikely to actually work but:
        if (schema != null) {
            r = r.with(schema);
        }
        CURRENCY_READER_DEFAULT = r;

        r = mapper.readerFor(Currency.class)
                .with(StreamReadFeature.USE_FAST_DOUBLE_PARSER);
        if (schema != null) {
            r = r.with(schema);
        }
        CURRENCY_READER_FAST = r;

        // And same for BigDecimal-variant
        r = mapper.readerFor(CurrencyBigDecimal.class);
        if (schema != null) {
            r = r.with(schema);
        }
        CURRENCY_BIGDEC_READER_DEFAULT = r;

        r = mapper.readerFor(CurrencyBigDecimal.class)
                .with(StreamReadFeature.USE_FAST_DOUBLE_PARSER)
                .with(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER);
        if (schema != null) {
            r = r.with(schema);
        }
        CURRENCY_BIGDEC_READER_FAST = r;
    }

    /*
    /**********************************************************************
    /* Secondary POJO tests
    /**********************************************************************
     */
    
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readCurrencyPojoDefault(Blackhole bh/*, AuxStateSize size*/) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.CURRENCY_WS), CURRENCY_READER_DEFAULT));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readCurrencyPojoFast(Blackhole bh/*, AuxStateSize size*/) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.CURRENCY_WS), CURRENCY_READER_FAST));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readCurrencyBigDecPojoDefault(Blackhole bh/*, AuxStateSize size*/) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.CURRENCY_WS), CURRENCY_BIGDEC_READER_DEFAULT));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readCurrencyBigDecPojoFast(Blackhole bh/*, AuxStateSize size*/) throws Exception {
        bh.consume(read(FULL_CONVERTER.bytes(InputData.CURRENCY_WS), CURRENCY_BIGDEC_READER_FAST));
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

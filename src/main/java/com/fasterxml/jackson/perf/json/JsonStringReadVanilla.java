package com.fasterxml.jackson.perf.json;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.*;
import com.fasterxml.jackson.perf.data.InputData;
import com.fasterxml.jackson.perf.data.StringInputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

/**
 * Alternative version that reads input using {@link java.io.Reader} instead of
 * {@link java.io.InputStream}.
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonStringReadVanilla
// NOTE: important -- can NOT extend ReaderPerfBaseBasicJackson, jmh dislikes
// overrides
    implements ReadPerfTestFull
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // pass non-null ObjectMapper: will remove whitespace, if any
    private final static StringInputConverter STRING_CONVERTER = StringInputConverter.stdConverter(MAPPER);

    protected final ObjectReader UNTYPED_READER;
    protected final ObjectReader NODE_READER;
    protected final ObjectReader MEDIA_ITEM_READER;
    
    protected final StringInputConverter _converter;

    public JsonStringReadVanilla() {
//        super(MediaItem.class, STRING_CONVERTER, MAPPER);
        _converter = STRING_CONVERTER;
        UNTYPED_READER = MAPPER.readerFor(Object.class);
        NODE_READER = MAPPER.readerFor(JsonNode.class);
        MEDIA_ITEM_READER = MAPPER.readerFor(MediaItem.class);
    }

    protected Object read(String input, ObjectReader reader) throws IOException {
        return reader.readValue(input);
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
        bh.consume(read(_converter.mediaItemAsString(), MEDIA_ITEM_READER));
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
        bh.consume(read(_converter.asString(InputData.CITM_CATALOG_WS), UNTYPED_READER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedWebxml(Blackhole bh) throws Exception {
        bh.consume(read(_converter.asString(InputData.WEBXML_WS), UNTYPED_READER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedMenu(Blackhole bh) throws Exception {
        bh.consume(read(_converter.asString(InputData.MENU_WS), UNTYPED_READER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedMediaItem(Blackhole bh) throws Exception {
        bh.consume(read(_converter.mediaItemAsString(), UNTYPED_READER));
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
        bh.consume(read(_converter.asString(InputData.CITM_CATALOG_WS), NODE_READER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeWebxml(Blackhole bh) throws Exception {
        bh.consume(read(_converter.asString(InputData.WEBXML_WS), NODE_READER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeMenu(Blackhole bh) throws Exception {
        bh.consume(read(_converter.asString(InputData.MENU_WS), NODE_READER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeMediaItem(Blackhole bh) throws Exception {
        bh.consume(read(_converter.mediaItemAsString(), NODE_READER));
    }
}

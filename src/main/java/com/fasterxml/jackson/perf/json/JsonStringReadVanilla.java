package com.fasterxml.jackson.perf.json;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.logic.BlackHole;

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
//@State(Scope.Group)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonStringReadVanilla
    extends ReadPerfBaseBasicJackson<MediaItem>
    implements ReadPerfTestFull
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // pass non-null ObjectMapper: will remove whitespace, if any
    private final static StringInputConverter STRING_CONVERTER = StringInputConverter.stdConverter(MAPPER);

    protected final ObjectReader UNTYPED_READER;
    protected final ObjectReader NODE_READER;
    
    protected final StringInputConverter _converter;
    
    public JsonStringReadVanilla() {
        super(MediaItem.class, STRING_CONVERTER, MAPPER);
        _converter = STRING_CONVERTER;
        UNTYPED_READER = MAPPER.readerFor(Object.class);
        NODE_READER = MAPPER.readerFor(JsonNode.class);
    }

    // We _should_ override all methods that would call this, so report an error here
    @Override
    protected Object read(byte[] input, ObjectReader reader) throws IOException {
        throw new IOException("Should never call this method for "+getClass().getName());
    }

    protected Object read(String input, ObjectReader reader) throws IOException {
        return reader.readValue(input);
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
        bh.consume(read(_converter.mediaItemAsString(), MEDIA_ITEM_READER));
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
        bh.consume(read(_converter.asString(InputData.CITM_CATALOG_WS), UNTYPED_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedWebxml(BlackHole bh) throws Exception {
        bh.consume(read(_converter.asString(InputData.WEBXML_WS), UNTYPED_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedMenu(BlackHole bh) throws Exception {
        bh.consume(read(_converter.asString(InputData.MENU_WS), UNTYPED_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readUntypedMediaItem(BlackHole bh) throws Exception {
        bh.consume(read(_converter.mediaItemAsString(), UNTYPED_READER));
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
        bh.consume(read(_converter.asString(InputData.CITM_CATALOG_WS), NODE_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeWebxml(BlackHole bh) throws Exception {
        bh.consume(read(_converter.asString(InputData.WEBXML_WS), NODE_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeMenu(BlackHole bh) throws Exception {
        bh.consume(read(_converter.asString(InputData.MENU_WS), NODE_READER));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Override
    public void readNodeMediaItem(BlackHole bh) throws Exception {
        bh.consume(read(_converter.mediaItemAsString(), NODE_READER));
    }
}

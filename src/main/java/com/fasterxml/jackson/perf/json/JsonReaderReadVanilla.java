package com.fasterxml.jackson.perf.json;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

/**
 * Alternative version that reads input using {@link java.io.Reader} instead of
 * {@link java.io.InputStream}.
 */
@State(Scope.Group) // Thread, Group or Benchmark
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonReaderReadVanilla
    extends ReadPerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // pass non-null ObjectMapper: will remove whitespace, if any
    private final static InputConverter JSON_CONV = InputConverter.stdConverter(MAPPER);

    public JsonReaderReadVanilla() {
        super(MediaItem.class, JSON_CONV, MAPPER);
    }

    protected Object read(byte[] input, ObjectReader reader) throws IOException {
        return reader.readValue(new InputStreamReader(new ByteArrayInputStream(input), "UTF-8"));
    }
}

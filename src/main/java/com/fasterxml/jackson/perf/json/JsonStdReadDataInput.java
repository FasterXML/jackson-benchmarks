package com.fasterxml.jackson.perf.json;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.ReadPerfBaseFullJackson;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.model.MediaItem;

/**
 * Variant that uses {link java.io.DataInput}-backed input source
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonStdReadDataInput
    extends ReadPerfBaseFullJackson<MediaItem>
{
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // pass non-null ObjectMapper: will remove whitespace, if any
    private final static InputConverter JSON_CONV = InputConverter.stdConverter(MAPPER);

    // NOTE: to _RETAIN_ whitespace, we'd use:
//    private final static InputConverter JSON_CONV = InputConverter.nopConverter(MAPPER);
    
    public JsonStdReadDataInput() {
        super(MediaItem.class, JSON_CONV, MAPPER);
    }

    @Override
    protected Object read(byte[] input, ObjectReader reader) throws IOException {
        DataInput dataInput = new MockDataInput(input);
        JsonParser p = reader.getFactory().createParser(dataInput);
        Object ob = reader.readValue(p);
        p.close();
        return ob;
    }
}

class MockDataInput implements DataInput
{
    private final InputStream _input;

    public MockDataInput(byte[] data) {
        _input = new ByteArrayInputStream(data);
    }

    public MockDataInput(String utf8Data) throws IOException {
        _input = new ByteArrayInputStream(utf8Data.getBytes("UTF-8"));
    }

    public MockDataInput(InputStream in) {
        _input = in;
    }
    
    @Override
    public void readFully(byte[] b) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void readFully(byte[] b, int off, int len) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int skipBytes(int n) throws IOException {
        return (int) _input.skip(n);
    }

    @Override
    public boolean readBoolean() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte readByte() throws IOException {
        int ch = _input.read();
        if (ch < 0) {
            throw new IOException("End-of-input for readByte()");
        }
        return (byte) ch;
    }

    @Override
    public int readUnsignedByte() throws IOException {
        return readByte() & 0xFF;
    }

    @Override
    public short readShort() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int readUnsignedShort() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public char readChar() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int readInt() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public long readLong() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public float readFloat() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public double readDouble() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String readLine() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String readUTF() throws IOException {
        throw new UnsupportedOperationException();
    }
}
    
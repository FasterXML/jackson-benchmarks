package com.fasterxml.jackson.perf.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.EnumMap;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.model.MediaItems;

/**
 * Alternative to {@link InputConverter} used when input read via Reader,
 * or directly from String.
 */
public class StringInputConverter
    extends InputConverter
{
    protected final static Charset UTF8 = Charset.forName("UTF-8");

    protected final EnumMap<InputData, String> _strings;
    
    protected StringInputConverter(EnumMap<InputData,String> genericData,
    		byte[] mib)
	{
        super(null, mib);
        _strings = genericData;
	}

    public static StringInputConverter nopConverter(ObjectMapper targetMapper)
    {
        EnumMap<InputData,String> data = new EnumMap<>(InputData.class);

        // special case, mostly just to support non-converted JSON
        for (InputData input : InputData.values()) {
            data.put(input, new String(input.bytes(), UTF8));
        }
        // but can't avoid serializing typed values
        try {
	        byte[] mib = targetMapper.writeValueAsBytes(MediaItems.stdMediaItem());
	        return new StringInputConverter(data, mib);
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }

    public static StringInputConverter stdConverter(ObjectMapper targetMapper)
    {
        EnumMap<InputData,String> data = new EnumMap<>(InputData.class);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(4000);
        final JsonFactory jsonF = new JsonFactory();
        final TokenStreamFactory targetF = targetMapper.getFactory();

        try {
	        for (InputData input : InputData.values()) {
	            JsonParser in = jsonF.createParser(input.bytes());
	            bytes.reset();
	            JsonGenerator out = targetF.createGenerator(bytes);
	            while (in.nextToken() != null) {
	                out.copyCurrentStructure(in);
	            }
	            in.close();
	            out.close();
	            data.put(input, bytes.toString("UTF-8"));
	        }
	        byte[] mib = targetMapper.writeValueAsBytes(MediaItems.stdMediaItem());
	        return new StringInputConverter(data, mib);
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] bytes(InputData type) {
        throw new UnsupportedOperationException("Should not be called for "+getClass().getName());
    }

    public String asString(InputData type) {
        return _strings.get(type);
    }
}

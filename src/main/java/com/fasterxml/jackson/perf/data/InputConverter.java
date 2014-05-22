package com.fasterxml.jackson.perf.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumMap;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.perf.model.MediaItems;

public class InputConverter
{
    protected final EnumMap<InputData, byte[]> _data;

    protected final byte[] _mediaItemBytes;

    protected InputConverter(EnumMap<InputData, byte[]> genericData,
    		byte[] mib)
	{
    	_data = genericData;
    	_mediaItemBytes = mib;
	}

    public static InputConverter nopConverter(ObjectMapper targetMapper)
    {
        EnumMap<InputData, byte[]> data = new EnumMap<>(InputData.class);

        // special case, mostly just to support non-converted JSON
        for (InputData input : InputData.values()) {
        	data.put(input, input.bytes());
        }
        // but can't avoid serializing typed values
        try {
	        byte[] mib = targetMapper.writeValueAsBytes(MediaItems.stdMediaItem());
	        return new InputConverter(data, mib);
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }
    
    public static InputConverter stdConverter(ObjectMapper targetMapper)
    {
        EnumMap<InputData, byte[]> data = new EnumMap<>(InputData.class);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(4000);
        final JsonFactory jsonF = new JsonFactory();
        final JsonFactory targetF = targetMapper.getFactory();

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
	            data.put(input, bytes.toByteArray());
	        }
	        byte[] mib = targetMapper.writeValueAsBytes(MediaItems.stdMediaItem());
	        return new InputConverter(data, mib);
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }

    public byte[] bytes(InputData type) {
        return _data.get(type);
    }

    public byte[] bytesForMediaItem() {
        return _mediaItemBytes;
    }
}

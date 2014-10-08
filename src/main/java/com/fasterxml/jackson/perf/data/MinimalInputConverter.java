package com.fasterxml.jackson.perf.data;

import java.io.IOException;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.perf.model.MediaItems;

public class MinimalInputConverter
{
    protected final byte[] _mediaItemBytes;

    protected MinimalInputConverter(byte[] mib)
	{
    	_mediaItemBytes = mib;
	}

    public static MinimalInputConverter minimalConverter(ObjectMapper targetMapper) {
    	return minimalConverter(targetMapper, null);
    }

    public static MinimalInputConverter minimalConverter(ObjectMapper targetMapper,
    		FormatSchema schema)
    {
    	ObjectWriter w = targetMapper.writer();
    	if (schema != null) {
    		w = w.withSchema(schema);
    	}
        try {
	        return new MinimalInputConverter(w.writeValueAsBytes(MediaItems.stdMediaItem()));
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }
    
    public byte[] bytesForMediaItem() {
        return _mediaItemBytes;
    }
}

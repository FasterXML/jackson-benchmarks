package com.fasterxml.jackson.perf.data;

import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.perf.model.MediaItems;

public class MinimalInputConverter
{
    protected final byte[] _mediaItemBytes;

    protected final String _mediaItemString;
    
    protected MinimalInputConverter(byte[] mib)
    {
        _mediaItemBytes = mib;
        try {
            _mediaItemString = new String(mib, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static MinimalInputConverter minimalConverter(ObjectMapper targetMapper) {
        return minimalConverter(targetMapper, null);
    }

    public static MinimalInputConverter minimalConverter(ObjectMapper targetMapper,
    		FormatSchema schema)
    {
        return minimalConverter(targetMapper, schema, MediaItems.stdMediaItem());
    }

    public static MinimalInputConverter minimalConverter(ObjectMapper targetMapper,
            FormatSchema schema, Object value)
    {
        ObjectWriter w = targetMapper.writer();
        if (schema != null) {
            w = w.with(schema);
        }
        return new MinimalInputConverter(w.writeValueAsBytes(value));
    }

    public byte[] mediaItemAsBytes() {
        return _mediaItemBytes;
    }

    public String mediaItemAsString() {
        return _mediaItemString;
    }
}

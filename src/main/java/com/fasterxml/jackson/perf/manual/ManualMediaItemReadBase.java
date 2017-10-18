package com.fasterxml.jackson.perf.manual;

import java.io.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

public class ManualMediaItemReadBase extends ObjectReaderTestBase
{
	private final static MediaItem ITEM = MediaItems.stdMediaItem();

	private final ObjectMapper _mapper;
	private final boolean _useBytes;
	private final String _descPrefix;
	
	public ManualMediaItemReadBase(ObjectMapper m, boolean useBytes, String desc) {
		_mapper = m;
		_useBytes = useBytes;
		_descPrefix = desc;
	}
	
	@Override
    protected int targetSizeMegs() { return 15; }
    
    protected final void test() throws Exception
    {
        if (_useBytes) {
        	testFromBytes(
            		_mapper, "Media-item1/"+_descPrefix, ITEM, MediaItem.class
            		,_mapper, "Media-item2/"+_descPrefix, ITEM, MediaItem.class
              );
        } else {
        	testFromString(
            		_mapper, "Media-item1/"+_descPrefix, ITEM, MediaItem.class
            		,_mapper, "Media-item2/"+_descPrefix, ITEM, MediaItem.class
        			);
        	
        }
    }

    @Override
    protected double testDeser1(int reps, byte[] input, ObjectReader reader) throws IOException {
        return _testRawDeser(reps, input, reader);
    }

    @Override
    protected double testDeser2(int reps, byte[] input, ObjectReader reader) throws IOException {
        return _testRawDeser(reps, input, reader);
    }
    
    protected final double _testRawDeser(int reps, byte[] json, ObjectReader reader) throws IOException
    {
        long start = System.nanoTime();
        while (--reps >= 0) {
            JsonParser p = reader.createParser(new ByteArrayInputStream(json));
            JsonToken t;
            while ((t = p.nextToken()) != null) {
                if (t == JsonToken.VALUE_STRING) {
                    p.getText();
                } else if (t.isNumeric()) {
                    p.getNumberValue();
                }
                ;
            }
            p.close();
        }
        long nanos = System.nanoTime();
        hash = (int) nanos;
        return _msecsFromNanos(nanos - start);
    }
}

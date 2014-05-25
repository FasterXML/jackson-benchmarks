package com.fasterxml.jackson.perf.manual;

import java.io.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.perf.model.MediaItem;
import com.fasterxml.jackson.perf.model.MediaItems;

public class ManualMediaItemRead extends ObjectReaderTestBase
{
	private final static MediaItem ITEM = MediaItems.stdMediaItem();

	private final ObjectMapper _mapper;
	private final boolean _useBytes;
	private final String _descPrefix;
	
	public ManualMediaItemRead(ObjectMapper m, boolean useBytes, String desc) {
		_mapper = m;
		_useBytes = useBytes;
		_descPrefix = desc;
	}
	
	@Override
    protected int targetSizeMegs() { return 15; }
    
    public static void main(String[] args) throws Exception
    {
    	final boolean USE_BYTES = true;
    	final boolean USE_SMILE = true;
    	final boolean USE_AFTERBURNER = true;

    	if (args.length != 0) {
            System.err.println("Usage: java <class>");
            System.exit(1);
        }
        JsonFactory f = USE_SMILE ? new SmileFactory() : new JsonFactory();
        String desc = USE_SMILE ? "Smile" : "JSON";
        ObjectMapper m = new ObjectMapper(f);
        if (USE_AFTERBURNER) {
        	m.registerModule(new AfterburnerModule());
        	desc += "+Afterburner";
        }
        if (USE_BYTES) {
        	desc += "(bytes)";
        } else {
        	desc += "(String)";
        }
        new ManualMediaItemRead(m, USE_BYTES, desc).test();
    }
    
    private final void test() throws Exception
    {
        if (_useBytes) {
        	testFromBytes(
            		_mapper, "Media-item1/"+_descPrefix, ITEM, MediaItem.class
            		,_mapper, "Media-item2"+_descPrefix, ITEM, MediaItem.class
              );
        } else {
        	testFromString(
            		_mapper, "Media-item1/"+_descPrefix, ITEM, MediaItem.class
            		,_mapper, "Media-item2"+_descPrefix, ITEM, MediaItem.class
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
        final JsonFactory f = reader.getFactory();
        while (--reps >= 0) {
            JsonParser p = f.createParser(new ByteArrayInputStream(json));
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
        hash = f.hashCode();
        return _msecsFromNanos(System.nanoTime() - start);
    }
}

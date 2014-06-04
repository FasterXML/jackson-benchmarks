package com.fasterxml.jackson.perf.manual;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

public class MediaItemReadCBOR extends ManualMediaItemReadBase
{
	final static boolean USE_AFTERBURNER = true;

	public MediaItemReadCBOR(ObjectMapper m, boolean useBytes, String desc) {
		super(m, useBytes, desc);
	}

	public static void main(String[] args) throws Exception
    {
    	JsonFactory f = new CBORFactory();
        String desc = "CBOR";
        ObjectMapper m = new ObjectMapper(f);
        if (USE_AFTERBURNER) {
        	m.registerModule(new AfterburnerModule());
        	desc += "+Afterburner";
        }
        // true -> always use bytes
        new MediaItemReadCBOR(m, true, desc).test();
    }
}

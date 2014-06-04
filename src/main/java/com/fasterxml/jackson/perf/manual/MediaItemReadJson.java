package com.fasterxml.jackson.perf.manual;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

public class MediaItemReadJson extends ManualMediaItemReadBase
{
	public MediaItemReadJson(ObjectMapper m, boolean useBytes, String desc) {
		super(m, useBytes, desc);
	}

	public static void main(String[] args) throws Exception
    {
    	final boolean USE_BYTES = true;
    	final boolean USE_AFTERBURNER = true;

    	JsonFactory f = new JsonFactory();
        String desc = "JSON";
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
        new MediaItemReadJson(m, USE_BYTES, desc).test();
    }
}

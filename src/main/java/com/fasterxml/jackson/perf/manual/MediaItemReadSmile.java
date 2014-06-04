package com.fasterxml.jackson.perf.manual;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

public class MediaItemReadSmile extends ManualMediaItemReadBase
{
	final static boolean USE_AFTERBURNER = true;

	public MediaItemReadSmile(ObjectMapper m, boolean useBytes, String desc) {
		super(m, useBytes, desc);
	}

	public static void main(String[] args) throws Exception
    {
    	JsonFactory f = new SmileFactory();
        String desc = "Smile";
        ObjectMapper m = new ObjectMapper(f);
        if (USE_AFTERBURNER) {
        	m.registerModule(new AfterburnerModule());
        	desc += "+Afterburner";
        }
        // true -> always use bytes
        new MediaItemReadSmile(m, true, desc).test();
    }
}

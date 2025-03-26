package com.fasterxml.jackson.manualtest;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.smile.SmileFactory;
import tools.jackson.dataformat.smile.SmileMapper;

public class MediaItemReadSmile extends ManualMediaItemReadBase
{
	final static boolean USE_AFTERBURNER = true;

	public MediaItemReadSmile(ObjectMapper m, boolean useBytes, String desc) {
		super(m, useBytes, desc);
	}

	public static void main(String[] args) throws Exception
	{
	    String desc = "Smile";
	    ObjectMapper m = _mapper(SmileMapper.builder(new SmileFactory()), USE_AFTERBURNER);
	    if (USE_AFTERBURNER) {
	        desc += "+Afterburner";
	    }
	    // true -> always use bytes
	    new MediaItemReadSmile(m, true, desc).test();
    }
}

package com.fasterxml.jackson.manualtest;

import com.fasterxml.jackson.core.json.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class MediaItemReadJson extends ManualMediaItemReadBase
{
    public MediaItemReadJson(ObjectMapper m, boolean useBytes, String desc) {
        super(m, useBytes, desc);
    }

    public static void main(String[] args) throws Exception
    {
        final boolean USE_BYTES = true;
        final boolean USE_AFTERBURNER = true;

        String desc = "JSON";
        ObjectMapper m = _mapper(JsonMapper.builder(new JsonFactory()), USE_AFTERBURNER);
        if (USE_AFTERBURNER) {
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

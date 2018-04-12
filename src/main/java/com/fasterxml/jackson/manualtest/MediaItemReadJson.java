package com.fasterxml.jackson.manualtest;

import com.fasterxml.jackson.core.json.JsonFactory;
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
        ObjectMapper m;
        if (USE_AFTERBURNER) {
            m = ObjectMapper.builder(f)
                    .addModule(new AfterburnerModule())
                    .build();
            desc += "+Afterburner";
        } else {
            m = new ObjectMapper(f);
        }
        if (USE_BYTES) {
            desc += "(bytes)";
        } else {
            desc += "(String)";
        }
        new MediaItemReadJson(m, USE_BYTES, desc).test();
    }
}

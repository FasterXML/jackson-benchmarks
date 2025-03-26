package com.fasterxml.jackson.manualtest;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.cbor.CBORFactory;
import tools.jackson.dataformat.cbor.CBORMapper;

public class MediaItemReadCBOR extends ManualMediaItemReadBase
{
    final static boolean USE_AFTERBURNER = true;

    public MediaItemReadCBOR(ObjectMapper m, boolean useBytes, String desc) {
        super(m, useBytes, desc);
    }

    public static void main(String[] args) throws Exception
    {
        String desc = "CBOR";
        ObjectMapper m = _mapper(CBORMapper.builder(new CBORFactory()), USE_AFTERBURNER);
        if (USE_AFTERBURNER) {
            desc += "+Afterburner";
        }
        // true -> always use bytes
        new MediaItemReadCBOR(m, true, desc).test();
    }
}

package com.fasterxml.jackson.manualtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;

public class MediaItemReadCBOR extends ManualMediaItemReadBase
{
    final static boolean USE_AFTERBURNER = true;

    public MediaItemReadCBOR(ObjectMapper m, boolean useBytes, String desc) {
        super(m, useBytes, desc);
    }

    public static void main(String[] args) throws Exception
    {
        String desc = "CBOR";
        ObjectMapper m = _mapper(new CBORFactory(), USE_AFTERBURNER);
        if (USE_AFTERBURNER) {
            desc += "+Afterburner";
        }
        // true -> always use bytes
        new MediaItemReadCBOR(m, true, desc).test();
    }
}

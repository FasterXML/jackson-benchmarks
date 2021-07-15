package com.fasterxml.jackson.perf.data;

import junit.framework.TestCase;

public class InputDataTest extends TestCase
{
    public void testByteInput() throws Exception
    {
        for (InputData data : InputData.values()) {
            final byte[] bytes = data.bytes();
            assertNotNull(bytes);
//            System.out.println("Format '"+data.name()+": "+bytes.length+" bytes");
        }
    }
}

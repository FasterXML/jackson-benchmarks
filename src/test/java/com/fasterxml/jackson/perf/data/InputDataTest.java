package com.fasterxml.jackson.perf.data;

import junit.framework.TestCase;

public class InputDataTest extends TestCase
{
    public void testByteInput() throws Exception
    {
        for (InputData data : InputData.values()) {
            assertNotNull(data.bytes());
        }
    }
}

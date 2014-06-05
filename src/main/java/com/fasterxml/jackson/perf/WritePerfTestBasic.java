package com.fasterxml.jackson.perf;

import org.openjdk.jmh.logic.BlackHole;

public interface WritePerfTestBasic
{
    public void writePojoMediaItem(BlackHole bh) throws Exception;
}

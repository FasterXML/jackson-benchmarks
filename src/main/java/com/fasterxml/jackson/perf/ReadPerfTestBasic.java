package com.fasterxml.jackson.perf;

import org.openjdk.jmh.logic.BlackHole;

public interface ReadPerfTestBasic
{
    public void readPojoMediaItem(BlackHole bh) throws Exception;
}

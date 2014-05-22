package com.fasterxml.jackson.perf;

import org.openjdk.jmh.logic.BlackHole;

public interface PerfTestLimited
{
    public void readPojoMediaItem(BlackHole bh) throws Exception;
}

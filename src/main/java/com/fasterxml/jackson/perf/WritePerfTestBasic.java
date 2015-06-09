package com.fasterxml.jackson.perf;

import org.openjdk.jmh.infra.Blackhole;

public interface WritePerfTestBasic
{
    public void writePojoMediaItem(Blackhole bh) throws Exception;
}

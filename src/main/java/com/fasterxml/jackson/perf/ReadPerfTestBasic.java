package com.fasterxml.jackson.perf;

import org.openjdk.jmh.infra.Blackhole;

public interface ReadPerfTestBasic
{
    public void readPojoMediaItem(Blackhole bh/*, AuxStateSize size*/)
        throws Exception;
}

package com.fasterxml.jackson.perf;

import org.openjdk.jmh.infra.Blackhole;

public interface WritePerfTestFull extends WritePerfTestBasic
{
    public void writeUntypedMediaItem(Blackhole bh) throws Exception;

    public void writeNodeMediaItem(Blackhole bh) throws Exception;

    public void writeCurrencyPojoDefault(Blackhole bh) throws Exception;
    public void writeCurrencyPojoFast(Blackhole bh) throws Exception;
}

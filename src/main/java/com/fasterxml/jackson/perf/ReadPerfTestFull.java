package com.fasterxml.jackson.perf;

import org.openjdk.jmh.infra.Blackhole;

public interface ReadPerfTestFull extends ReadPerfTestBasic
{
    public void readUntypedCitmCatalog(Blackhole bh) throws Exception;
    public void readUntypedWebxml(Blackhole bh) throws Exception;
    public void readUntypedMenu(Blackhole bh) throws Exception;
    public void readUntypedMediaItem(Blackhole bh) throws Exception;

    public void readNodeCitmCatalog(Blackhole bh) throws Exception;
    public void readNodeWebxml(Blackhole bh) throws Exception;
    public void readNodeMenu(Blackhole bh) throws Exception;
    public void readNodeMediaItem(Blackhole bh) throws Exception;

    public void readCurrencyPojoDefault(Blackhole bh) throws Exception;
    public void readCurrencyPojoFast(Blackhole bh) throws Exception;
}

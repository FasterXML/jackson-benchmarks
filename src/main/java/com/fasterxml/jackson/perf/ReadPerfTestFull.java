package com.fasterxml.jackson.perf;

import org.openjdk.jmh.logic.BlackHole;

public interface ReadPerfTestFull extends ReadPerfTestBasic
{
    public void readUntypedCitmCatalog(BlackHole bh) throws Exception;
    public void readUntypedWebxml(BlackHole bh) throws Exception;
    public void readUntypedMenu(BlackHole bh) throws Exception;
    public void readUntypedMediaItem(BlackHole bh) throws Exception;

    public void readNodeCitmCatalog(BlackHole bh) throws Exception;
    public void readNodeWebxml(BlackHole bh) throws Exception;
    public void readNodeMenu(BlackHole bh) throws Exception;
    public void readNodeMediaItem(BlackHole bh) throws Exception;
}

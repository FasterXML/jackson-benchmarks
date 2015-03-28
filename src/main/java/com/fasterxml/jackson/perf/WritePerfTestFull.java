package com.fasterxml.jackson.perf;

import org.openjdk.jmh.logic.BlackHole;

public interface WritePerfTestFull extends WritePerfTestBasic
{
    public void writeMapMediaItem(BlackHole bh) throws Exception;

    public void writeNodeMediaItem(BlackHole bh) throws Exception;
}

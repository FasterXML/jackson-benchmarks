package com.fasterxml.jackson.perf;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.perf.data.InputConverter;
import com.fasterxml.jackson.perf.data.InputData;

public abstract class PerfBase
{
    protected final InputConverter CONV;
    protected final ObjectReader READER;
    
    protected PerfBase(InputConverter conv, ObjectReader r)
    {
        READER = r;
        CONV = conv;
    }
    
    private Object process(byte[] input) throws IOException {
        return READER.readValue(input);
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readCitmCatalogWS(BlackHole bh) throws Exception {
        bh.consume(process(CONV.bytes(InputData.CITM_CATALOG_WS)));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readWebxmlWS(BlackHole bh) throws Exception {
        bh.consume(process(CONV.bytes(InputData.WEBXML_WS)));
    }

    @GenerateMicroBenchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void readMenuWS(BlackHole bh) throws Exception {
        bh.consume(process(CONV.bytes(InputData.MENU_WS)));
    }
}

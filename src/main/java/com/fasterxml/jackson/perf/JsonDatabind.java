package com.fasterxml.jackson.perf;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.logic.BlackHole;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

@State(Scope.Thread) // or Group or Benchmark
@OutputTimeUnit(TimeUnit.SECONDS)
public class JsonDatabind // extends PerfBase
{
    private static final ObjectReader JACKSON_READER = new ObjectMapper().reader(Object.class);

    private Object process(byte[] input) throws Exception {
        return JACKSON_READER.readValue(input);
    }

    @GenerateMicroBenchmark
    public void citmCatalogWS(BlackHole bh) throws Exception {
        bh.consume(process(TestFiles.CITM_CATALOG_WS.jsonBytes()));
    }

    @GenerateMicroBenchmark
    public void webxmlWS(BlackHole bh) throws Exception {
        bh.consume(process(TestFiles.WEBXML_WS.jsonBytes()));
    }

    @GenerateMicroBenchmark
    public void menuWS(BlackHole bh) throws Exception {
        bh.consume(process(TestFiles.MENU_WS.jsonBytes()));
    }
}

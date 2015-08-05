package com.fasterxml.jackson.perf.dzone;

import java.io.Writer;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.perf.util.NopWriter;

/**
 * Base class for tests inspired by
 * <a href="https://dzone.com/articles/compare-json-api">DZone Jackson vs GSON</a>
 * test.
 */
abstract class DZoneTestBase
{
    protected final static List<MeasurementRecord> list10 = MeasurementRecord.construct(10);
    protected final static List<MeasurementRecord> list1000 = MeasurementRecord.construct(1000);
    protected final static List<MeasurementRecord> list100000 = MeasurementRecord.construct(100000);
    
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void writeAsString10(Blackhole bh) throws Exception {
        bh.consume(_writeItems(list10, new NopWriter()));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void writeAsString1k(Blackhole bh) throws Exception {
        bh.consume(_writeItems(list1000, new NopWriter()));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void writeAsString100k(Blackhole bh) throws Exception {
        bh.consume(_writeItems(list100000, new NopWriter()));
    }

    public abstract int _writeItems(List<MeasurementRecord> items, Writer out) throws Exception;
}

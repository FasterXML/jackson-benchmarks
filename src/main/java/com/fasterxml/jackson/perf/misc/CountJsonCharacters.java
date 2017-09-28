package com.fasterxml.jackson.perf.misc;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

public class CountJsonCharacters
{
    private final static String INPUT = JsonEncodeBase.LONG_INPUT;

    private final static int LOCAL_REPS = 10;
    
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public int countUsingLoop(Blackhole bh) throws Exception
    {
        final StringBuilder sb = new StringBuilder();
        int count = LOCAL_REPS;
        int result = 0;
        while (--count >= 0) {
            sb.setLength(0);
            result += _countUsingLoop(sb, INPUT);
        }
        return result;
    }

    private long _countUsingLoop(StringBuilder sb, String value)
    {
        for (int i = 0, len = value.length(); i < len; ++i) {
            sb.append(value.charAt(i));
        }
        return sb.length();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public long countUsingStream(Blackhole bh) throws Exception
    {
        final StringBuilder sb = new StringBuilder();
        int count = LOCAL_REPS;
        int result = 0;
        while (--count >= 0) {
            sb.setLength(0);
            result += _countUsingStream(sb, INPUT);
        }
        return result;
    }

    private long _countUsingStream(final StringBuilder sb, String value)
    {
        value.chars().forEachOrdered((eachChar) -> {
            sb.append(eachChar);
        });
        return sb.length();
    }
}

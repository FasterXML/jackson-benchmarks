package com.fasterxml.jackson.perf.misc;

import java.io.Writer;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import com.fasterxml.jackson.perf.util.NopWriter;

/**
 * Base class for simple String encoding comparisons
 */
public abstract class JsonEncodeBase
{
    // contents copied from sample document in https://en.wikipedia.org/wiki/JSON
    // although augmented by some escapable characters
    public final static String[] INPUTS = new String[] {
            "firstName", "John",
            "lastName", "\"Smith\"",
            "age", "25",
            "address", "streetAddress", "21\n2nd Street",
            "city", "New\\York",
            "state", "NY",
            "postalCode", "10021",
              "phoneNumber",
                  "type", "home",
                  "number", "212/555-1234",
                  "type", "fax",
                  "number", "646\t555-4567",
              "gender",
                "type", "male"
    };

    public final static String LONG_INPUT;
    static {
        // inefficient but who cares:
        LONG_INPUT = Stream.of(INPUTS).collect(Collectors.joining(" "));
    }

    // While jmh should drive repetition for most part, let's do couple of calls
    // here to reduce effect of allocations
    private final static int LOCAL_REPS = 10;

    protected final static Writer NOP_WRITER = new NopWriter();
    
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public int encodeJsonNToBuilder(Blackhole bh) throws Exception
    {
        final StringBuilder sb = new StringBuilder();
        // to amortize cost of clear (and remove most allocation)
        int count = LOCAL_REPS;
        while (--count >= 0) {
            sb.setLength(0);
            encode(sb, INPUTS);
        }
        return sb.length();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public int encodeJson1ToBuilder(Blackhole bh) throws Exception
    {
        final StringBuilder sb = new StringBuilder();
        // to amortize cost of clear (and remove most allocation)
        int count = LOCAL_REPS;
        while (--count >= 0) {
            sb.setLength(0);
            encode(sb, LONG_INPUT);
        }
        return sb.length();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void encodeJsonNToNopWriter(Blackhole bh) throws Exception
    {
        int count = LOCAL_REPS;
        while (--count >= 0) {
            encode(NOP_WRITER, INPUTS);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void encodeJson1ToNopWriter(Blackhole bh) throws Exception
    {
        int count = LOCAL_REPS;
        while (--count >= 0) {
            encode(NOP_WRITER, LONG_INPUT);
        }
    }

    protected abstract void encode(StringBuilder sb, String[] values) throws Exception;
    protected abstract void encode(StringBuilder sb, String value) throws Exception;

    protected abstract void encode(Writer w, String[] values) throws Exception;
    protected abstract void encode(Writer w, String value) throws Exception;
}

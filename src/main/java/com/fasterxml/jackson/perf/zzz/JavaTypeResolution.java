package com.fasterxml.jackson.perf.zzz;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import tools.jackson.databind.type.TypeFactory;

@State(Scope.Thread)
public class JavaTypeResolution
{
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public Object resolveArrayList(Blackhole bh) throws Exception {
        TypeFactory tf = TypeFactory.defaultInstance().withModifier(null);
        return tf.constructType(ArrayList.class);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public Object resolveMapAny(Blackhole bh) throws Exception {
        TypeFactory tf = TypeFactory.defaultInstance().withModifier(null);
        return tf.constructType(Map.class);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public Object resolveMapLinkedHash(Blackhole bh) throws Exception {
        TypeFactory tf = TypeFactory.defaultInstance().withModifier(null);
        return tf.constructType(LinkedHashMap.class);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public Object resolveObject(Blackhole bh) throws Exception {
        TypeFactory tf = TypeFactory.defaultInstance().withModifier(null);
        return tf.constructType(Object.class);
    }
}

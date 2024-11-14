package com.fasterxml.jackson.perf.zzz;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.type.TypeFactory;
import tools.jackson.databind.util.LookupCache;

@State(Scope.Benchmark)
public class JavaTypeResolution
{
    @Param
    public CacheMode cache;
    private TypeFactory tf;

    @Setup
    public void setup() {
        tf = cache.apply(new TypeFactory().withModifier(null));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public JavaType resolveArrayList() {
        return tf.constructType(ArrayList.class);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public JavaType resolveMapAny() {
        return tf.constructType(Map.class);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public JavaType resolveMapLinkedHash() {
        return tf.constructType(LinkedHashMap.class);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public JavaType resolveObject() {
        return tf.constructType(Object.class);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public JavaType constructSpecializedType() {
        JavaType listOfCustomObject = tf.constructType(new TypeReference<List<CustomObject>>() {});
        return tf.constructSpecializedType(listOfCustomObject, CustomList.class);
    }

    public static final class CustomObject {}

    @SuppressWarnings("serial")
    public static final class CustomList<U> extends ArrayList<U> {}

    public enum CacheMode {
        DEFAULT() {
            @Override
            TypeFactory apply(TypeFactory input) {
                return input;
            }
        },
        NONE() {
            @Override
            TypeFactory apply(TypeFactory input) {
                return input.withCache(NoCacheLookupCache.INSTANCE);
            }
        };

        abstract TypeFactory apply(TypeFactory input);
    }

    private enum NoCacheLookupCache implements LookupCache<Object, JavaType> {
        INSTANCE;

        @Override
        public int size() {
            return 0;
        }

        @Override
        public JavaType get(Object o) {
            return null;
        }

        @Override
        public JavaType put(Object o, JavaType javaType) {
            return null;
        }

        @Override
        public JavaType putIfAbsent(Object o, JavaType javaType) {
            return null;
        }

        @Override
        public void clear() {}

        @Override
        public LookupCache<Object, JavaType> snapshot() {
            return this;
        }

        @Override
        public LookupCache<Object, JavaType> emptyCopy() {
            return this;
        }
    }
}

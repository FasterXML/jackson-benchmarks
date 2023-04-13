package com.fasterxml.jackson.perf.zzz;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.LookupCache;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import tools.jackson.databind.type.TypeFactory;

@State(Scope.Benchmark)
public class JavaTypeResolution
{

    @Param
    public CacheMode cache;
    private TypeFactory tf;

    @Setup
    public void setup() {
        tf = cache.apply(TypeFactory.defaultInstance().withModifier(null));
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

    public static final class CustomList<U> extends ArrayList<U> {}

    public enum CacheMode {
        @SuppressWarnings("unused") // used by JMH
        DEFAULT() {
            @Override
            TypeFactory apply(TypeFactory input) {
                return input;
            }
        },
        @SuppressWarnings("unused") // used by JMH
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
    }
}

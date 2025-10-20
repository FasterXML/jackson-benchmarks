package com.fasterxml.jackson.perf;

import tools.jackson.core.TSFBuilder;
import tools.jackson.core.TokenStreamFactory;
import tools.jackson.core.util.JsonRecyclerPools;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.cfg.MapperBuilder;

/**
 * Helper methods for configuring streaming factories, mappers; called
 * by format-specific performance test (base) classes.
 */
public interface PerfDefaultsGeneral
{
    public static <F extends TokenStreamFactory, B extends TSFBuilder<F, B>>
    B configureFactoryBuilder(B builder) {
        return builder
            //.recyclerPool(JsonRecyclerPools.threadLocalPool())
            //.recyclerPool(JsonRecyclerPools.nonRecyclingPool())
            //.recyclerPool(JsonRecyclerPools.newConcurrentDequePool())
            .recyclerPool(JsonRecyclerPools.newBoundedPool(10))
            ;
    }

    public static <M extends ObjectMapper, B extends MapperBuilder<M, B>>
    B configureMapperBuilder(B builder) {
        return builder.disable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
    }
}

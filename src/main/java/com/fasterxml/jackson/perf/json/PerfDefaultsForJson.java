package com.fasterxml.jackson.perf.json;

import tools.jackson.core.json.JsonFactory;
import tools.jackson.core.json.JsonFactoryBuilder;
import tools.jackson.core.json.JsonWriteFeature;
import tools.jackson.core.util.JsonRecyclerPools;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.json.JsonMapper;

interface PerfDefaultsForJson
{
    public static JsonFactoryBuilder jsonFactoryBuilder() {
        return JsonFactory.builder()
            .recyclerPool(
                    //JsonRecyclerPools.threadLocalPool()
                    JsonRecyclerPools.defaultPool()
            );
    }

    public static JsonFactory jsonFactory() {
        return jsonFactoryBuilder().build();
    }

    public static JsonMapper jsonMapper() {
        return jsonMapperBuilder().build();
    }
    
    public static JsonMapper.Builder jsonMapperBuilder() {
        return jsonMapperBuilder(jsonFactory());
    }

    public static JsonMapper.Builder jsonMapperBuilder(JsonFactory f) {
        return JsonMapper.builder(f)
                .disable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)
                .disable(JsonWriteFeature.ESCAPE_FORWARD_SLASHES);

    }
}

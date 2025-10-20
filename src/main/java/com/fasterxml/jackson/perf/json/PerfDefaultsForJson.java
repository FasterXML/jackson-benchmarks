package com.fasterxml.jackson.perf.json;

import com.fasterxml.jackson.perf.PerfDefaultsGeneral;

import tools.jackson.core.json.JsonFactory;
import tools.jackson.core.json.JsonFactoryBuilder;
import tools.jackson.databind.json.JsonMapper;

interface PerfDefaultsForJson
    extends PerfDefaultsGeneral
{
    static JsonFactoryBuilder jsonFactoryBuilder() {
        return PerfDefaultsGeneral.configureFactoryBuilder(JsonFactory.builder());
    }

    static JsonFactory jsonFactory() {
        return jsonFactoryBuilder().build();
    }

    static JsonMapper jsonMapper() {
        return jsonMapperBuilder().build();
    }
    
    static JsonMapper.Builder jsonMapperBuilder() {
        return jsonMapperBuilder(jsonFactory());
    }

    static JsonMapper.Builder jsonMapperBuilder(JsonFactory f) {
        return PerfDefaultsGeneral.configureMapperBuilder(JsonMapper.builder(f));
    }
}

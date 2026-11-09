package com.fasterxml.jackson.perf.bson;

import com.fasterxml.jackson.perf.PerfDefaultsGeneral;

import tools.jackson.databind.json.JsonMapper;

import de.undercouch.bson4jackson.BsonFactory;
import de.undercouch.bson4jackson.BsonModule;

interface PerfDefaultsForBSON
    extends PerfDefaultsGeneral
{
    static JsonMapper bsonMapper() {
        return bsonMapperBuilder().build();
    }

    // NOTE: bson4jackson has no format-specific mapper; BsonFactory extends JsonFactory
    static JsonMapper.Builder bsonMapperBuilder() {
        return PerfDefaultsGeneral.configureMapperBuilder(JsonMapper.builder(new BsonFactory()))
                .addModule(new BsonModule());
    }
}

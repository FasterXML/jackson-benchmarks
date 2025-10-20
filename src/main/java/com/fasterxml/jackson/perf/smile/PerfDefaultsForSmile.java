package com.fasterxml.jackson.perf.smile;

import com.fasterxml.jackson.perf.PerfDefaultsGeneral;

import tools.jackson.dataformat.smile.SmileFactory;
import tools.jackson.dataformat.smile.SmileFactoryBuilder;
import tools.jackson.dataformat.smile.SmileMapper;

interface PerfDefaultsForSmile
    extends PerfDefaultsGeneral
{
    static SmileFactoryBuilder smileFactoryBuilder() {
        return PerfDefaultsGeneral.configureFactoryBuilder(SmileFactory.builder());
    }

    static SmileFactory smileFactory() {
        return smileFactoryBuilder().build();
    }

    static SmileMapper smileMapper() {
        return smileMapperBuilder().build();
    }
    
    static SmileMapper.Builder smileMapperBuilder() {
        return smileMapperBuilder(smileFactory());
    }

    static SmileMapper.Builder smileMapperBuilder(SmileFactory f) {
        return PerfDefaultsGeneral.configureMapperBuilder(SmileMapper.builder(f));
    }
}

package com.fasterxml.jackson.perf.model;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.perf.data.InputData;

public class CurrencyBigDecimalSampleProvider
{
    private final static CurrencyBigDecimalSampleProvider INSTANCE
        = new CurrencyBigDecimalSampleProvider();

    private final RuntimeException _fail;

    private final CurrencyBigDecimal _sample;

    public static CurrencyBigDecimal getSample() {
        return INSTANCE._getSample();
    }

    CurrencyBigDecimalSampleProvider() {
        RuntimeException fail = null;
        CurrencyBigDecimal sample = null;
        try {
            byte[] json = InputData.CURRENCY_WS.bytes();
            sample = new JsonMapper().readerFor(CurrencyBigDecimal.class)
                .readValue(json);
        } catch (Exception e) {
            fail = new RuntimeException(e);
        }
        _fail = fail;
        _sample = sample;
    }
    
    CurrencyBigDecimal _getSample() {
        if (_fail != null) {
            throw _fail;
        }
        return _sample;
    }
}

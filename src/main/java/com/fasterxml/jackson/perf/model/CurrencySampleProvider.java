package com.fasterxml.jackson.perf.model;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.perf.data.InputData;

public class CurrencySampleProvider
{
    private final static CurrencySampleProvider INSTANCE = new CurrencySampleProvider();

    private final RuntimeException _fail;

    private final Currency _sample;
    
    public static Currency getSample() {
        return INSTANCE._getSample();
    }

    CurrencySampleProvider() {
        RuntimeException fail = null;
        Currency sample = null;
        try {
            byte[] json = InputData.CURRENCY_WS.bytes();
            sample = new JsonMapper().readerFor(Currency.class)
                .readValue(json);
        } catch (Exception e) {
            fail = new RuntimeException(e);
        }
        _fail = fail;
        _sample = sample;
    }
    
    Currency _getSample() {
        if (_fail != null) {
            throw _fail;
        }
        return _sample;
    }
}

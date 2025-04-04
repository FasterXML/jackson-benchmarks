package com.fasterxml.jackson.perf.model;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Model class for data from
 * <a href="https://www.exchangerate-api.com">Exchange Rate</a>;
 * see {@code ./json/USD.json} that uses {@link BigDecimal}
 * (instead of {@code Double})
 */
public class CurrencyBigDecimal {
    public String base, date, provider, terms;
    public int time_last_updated;

    public Map<String, BigDecimal> rates;
}

package com.fasterxml.jackson.perf.model;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Model class for data from
 * <a href="https://www.exchangerate-api.com">Exchange Rate</a>;
 * see {@code ./json/USD.json}.
 *
 */
@JsonIgnoreProperties({
    
})
public class Currency {
    public String base, date, provider, terms;
    public int time_last_updated;

    public Map<String, Double> rates;
}

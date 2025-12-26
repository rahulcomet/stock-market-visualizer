package com.example.stock_market_backend;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class StockDataDTO {

    // Map representing the "Time Series (Daily)" data.
    @JsonProperty("Time Series (Daily)")
    private Map<String, Map<String, String>> timeSeries;

    // Getters and setters
    public Map<String, Map<String, String>> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(Map<String, Map<String, String>> timeSeries) {
        this.timeSeries = timeSeries;
    }
}

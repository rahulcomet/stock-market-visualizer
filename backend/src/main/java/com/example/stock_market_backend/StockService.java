package com.example.stock_market_backend;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Value;

@Service
public class StockService {

    private final WebClient webClient;
    private final String apiKey;

    public StockService(@Value("${ALPHAVANTAGE_API_KEY}") String apiKey) {
        this.apiKey = apiKey;
        this.webClient = WebClient.builder()
            .baseUrl("https://www.alphavantage.co")
            .build();
    }


    public Mono<StockDataDTO> getStockData(String symbol) {
        // Example endpoint: https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=YOUR_KEY
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/query")
                .queryParam("function", "TIME_SERIES_DAILY")
                .queryParam("symbol", symbol)
                .queryParam("apikey", apiKey)
                .build())
            .retrieve()
            .bodyToMono(StockDataDTO.class);
    }
}

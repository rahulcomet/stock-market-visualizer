package com.example.stock_market_backend.alpha;

import com.example.stock_market_backend.news.NewsItem;
import com.example.stock_market_backend.news.TickerSentiment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AlphaNewsClient {
    private final WebClient http;
    private final String apiKey;

    // DTOs for Jackson deserialization
    @JsonIgnoreProperties(ignoreUnknown = true)
    private record AlphaTickerSentiment(
            String ticker,
            @JsonProperty("relevance_score") Double relevanceScore,
            @JsonProperty("ticker_sentiment_score") Double tickerSentimentScore,
            @JsonProperty("ticker_sentiment_label") String tickerSentimentLabel
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record AlphaNewsItem(
            String title,
            String url,
            String summary,
            @JsonProperty("time_published") @JsonFormat(pattern = "yyyyMMdd'T'HHmmss") LocalDateTime timePublished,
            @JsonProperty("overall_sentiment_score") Double overallSentimentScore,
            @JsonProperty("overall_sentiment_label") String overallSentimentLabel,
            @JsonProperty("ticker_sentiment") List<AlphaTickerSentiment> tickerSentiment
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record AlphaNewsResponse(List<AlphaNewsItem> feed) {}

    public AlphaNewsClient(@Value("${ALPHAVANTAGE_API_KEY}") String apiKey) {
        this.apiKey = apiKey;
        this.http = WebClient.builder()
                .baseUrl("https://www.alphavantage.co")
                .build();
    }

    public List<NewsItem> fetchNews(String tickers, String timeFrom, String timeTo, String sort, int limit) {
        int effectiveLimit = (limit > 0) ? Math.min(limit, 1000) : 50;

        AlphaNewsResponse response = http.get().uri(b -> {
            b.path("/query")
                    .queryParam("function", "NEWS_SENTIMENT")
                    .queryParam("apikey", apiKey)
                    .queryParam("limit", effectiveLimit);
            if (tickers != null && !tickers.isBlank()) b.queryParam("tickers", tickers);
            if (timeFrom != null && !timeFrom.isBlank()) b.queryParam("time_from", timeFrom);
            if (timeTo != null && !timeTo.isBlank()) b.queryParam("time_to", timeTo);
            if (sort != null && !sort.isBlank()) b.queryParam("sort", sort);
            return b.build();
        }).retrieve().bodyToMono(AlphaNewsResponse.class).block();

        return Optional.ofNullable(response)
                .map(AlphaNewsResponse::feed)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toNewsItem)
                .collect(Collectors.toList());
    }

    private NewsItem toNewsItem(AlphaNewsItem alphaItem) {
        List<TickerSentiment> sentiments = Optional.ofNullable(alphaItem.tickerSentiment())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toTickerSentiment)
                .collect(Collectors.toList());

        return new NewsItem(
                alphaItem.title(),
                alphaItem.url(),
                alphaItem.summary(),
                alphaItem.timePublished(),
                alphaItem.overallSentimentScore(),
                alphaItem.overallSentimentLabel(),
                sentiments
        );
    }

    private TickerSentiment toTickerSentiment(AlphaTickerSentiment alphaSentiment) {
        return new TickerSentiment(
                alphaSentiment.ticker(),
                alphaSentiment.relevanceScore(),
                alphaSentiment.tickerSentimentScore(),
                alphaSentiment.tickerSentimentLabel()
        );
    }
}

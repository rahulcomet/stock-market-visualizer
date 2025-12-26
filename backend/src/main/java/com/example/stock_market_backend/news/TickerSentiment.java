package com.example.stock_market_backend.news;

/**
 * Sentiment metrics for a specific ticker mentioned in a news article.
 */
public record TickerSentiment(
    String ticker,
    Double relevanceScore,
    Double tickerSentimentScore,
    String tickerSentimentLabel
) {}
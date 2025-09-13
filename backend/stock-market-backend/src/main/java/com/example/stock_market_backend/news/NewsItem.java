package com.example.stock_market_backend.news;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents one Alpha Vantage news feed item.
 * Includes title, URL, summary, published timestamp, sentiment, and ticker-level sentiments.
 */

public record NewsItem(
  String title,
  String url,
  String summary,
  LocalDateTime timePublished,
  Double overallSentimentScore,
  String overallSentimentLabel,
  List<TickerSentiment> tickerSentiment
) {}

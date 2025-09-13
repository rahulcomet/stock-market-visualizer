package com.example.stock_market_backend.news;

import com.example.stock_market_backend.alpha.AlphaNewsClient;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "http://localhost:4200")
public class NewsController {
  private final AlphaNewsClient alpha;
  public NewsController(AlphaNewsClient alpha) { this.alpha = alpha; }

  // GET /api/news/top3?ticker=AAPL
  @GetMapping("/top3")
  public List<NewsItem> top3(@RequestParam String ticker) {
    var items = alpha.fetchNews(ticker, null, null, "LATEST", 12);
    return items.stream()
        .sorted(Comparator.comparing(NewsItem::timePublished,
            Comparator.nullsLast(Comparator.naturalOrder())).reversed())
        .limit(3)
        .toList();
  }
}

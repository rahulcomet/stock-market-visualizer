package com.example.stock_market_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping(value = "/api/stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<StockDataDTO> getStock(@RequestParam String symbol) {
        return stockService.getStockData(symbol);
    }
}

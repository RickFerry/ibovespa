package com.mazza.ibovespa.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mazza.ibovespa.service.TradeService;

@RestController
@RequestMapping("/trades")
public class TradeController {
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @GetMapping
    public List<String> categorizeTrades(@RequestParam String referenceDate) {
        LocalDate refDate = LocalDate.parse(referenceDate);
        return tradeService.getAllTrades().stream()
                .map(trade -> tradeService.categorizeTrade(trade, refDate).toString())
                .toList();
    }
}

package com.mazza.ibovespa.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.mazza.ibovespa.model.Trade;
import com.mazza.ibovespa.model.enums.RiskCategory;
import com.mazza.ibovespa.repository.TradeRepository;

@Service
public class TradeService {
    private final TradeRepository tradeRepository;

    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public RiskCategory categorizeTrade(Trade trade, LocalDate referenceDate) {
        return Stream.of(
                new CategorizationRule(t -> ChronoUnit.DAYS.between(t.getNextPaymentDate(), referenceDate) > 30,
                        RiskCategory.EXPIRED),
                new CategorizationRule(
                        t -> t.getTradeValue() > 1_000_000 && "Private".equalsIgnoreCase(t.getClientSector()),
                        RiskCategory.HIGHRISK),
                new CategorizationRule(
                        t -> t.getTradeValue() > 1_000_000 && "Public".equalsIgnoreCase(t.getClientSector()),
                        RiskCategory.MEDIUMRISK))
                .filter(rule -> rule.predicate.apply(trade))
                .map(rule -> rule.category)
                .findFirst()
                .orElse(null);
    }

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    private static class CategorizationRule {
        private final Function<Trade, Boolean> predicate;
        private final RiskCategory category;

        public CategorizationRule(Function<Trade, Boolean> predicate, RiskCategory category) {
            this.predicate = predicate;
            this.category = category;
        }
    }
}

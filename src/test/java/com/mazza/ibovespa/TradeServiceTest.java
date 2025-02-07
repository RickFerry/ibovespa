package com.mazza.ibovespa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.mazza.ibovespa.model.Trade;
import com.mazza.ibovespa.model.enums.RiskCategory;
import com.mazza.ibovespa.repository.TradeRepository;
import com.mazza.ibovespa.service.TradeService;

@SpringBootTest
class TradeServiceTest {

    private TradeService tradeService;

    @BeforeEach
    void setup() {
        TradeRepository tradeRepository = mock(TradeRepository.class);
        tradeService = new TradeService(tradeRepository);
    }

    @Test
    void testExpiredTrade() {
        Trade trade = new Trade(1L, 500000, "Public", LocalDate.now().minusDays(40));
        RiskCategory category = tradeService.categorizeTrade(trade, LocalDate.now());
        assertEquals(RiskCategory.EXPIRED, category);
    }

    @Test
    void testHighRiskTrade() {
        Trade trade = new Trade(2L, 2000000, "Private", LocalDate.now().plusDays(10));
        RiskCategory category = tradeService.categorizeTrade(trade, LocalDate.now());
        assertEquals(RiskCategory.HIGHRISK, category);
    }

    @Test
    void testMediumRiskTrade() {
        Trade trade = new Trade(3L, 2000000, "Public", LocalDate.now().plusDays(10));
        RiskCategory category = tradeService.categorizeTrade(trade, LocalDate.now());
        assertEquals(RiskCategory.MEDIUMRISK, category);
    }
}

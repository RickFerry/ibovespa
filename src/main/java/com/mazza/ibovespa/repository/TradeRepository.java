package com.mazza.ibovespa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mazza.ibovespa.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
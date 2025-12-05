package com.example.banking.domain.port;

import com.example.banking.domain.model.Stock;

import java.util.List;
import java.util.Optional;

public interface StockRepository {
    List<Stock> findAll();

    Optional<Stock> findById(String id);

    //Trouve une action par son symbole boursier
    Optional<Stock> findBySymbol(String symbol);
    Stock save(Stock stock);

    boolean existsBySymbol(String symbol);
}
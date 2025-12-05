package com.example.banking.domain.usecase;

import com.example.banking.domain.model.Stock;
import com.example.banking.domain.port.StockRepository;

import java.util.List;


public class ListStocks {

    private final StockRepository stockRepository;

    public ListStocks(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> execute() {
        return stockRepository.findAll();
    }
}

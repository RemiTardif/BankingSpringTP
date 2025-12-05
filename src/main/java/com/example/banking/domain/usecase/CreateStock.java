package com.example.banking.domain.usecase;

import com.example.banking.domain.exception.StockAlreadyExistsException;
import com.example.banking.domain.model.Stock;
import com.example.banking.domain.port.StockRepository;

//Use case : Créer une nouvelle action boursière
public class CreateStock {

    private final StockRepository stockRepository;

    public CreateStock(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

//Crée une nouvelle action
    public Stock execute(String symbol, String name, double price) {
        // Vérifie que le symbole n'existe pas déjà
        if (stockRepository.existsBySymbol(symbol)) {
            throw new StockAlreadyExistsException(symbol);
        }

        // Crée et sauvegarde le stock
        Stock stock = Stock.create(symbol, name, price);
        return stockRepository.save(stock);
    }
}
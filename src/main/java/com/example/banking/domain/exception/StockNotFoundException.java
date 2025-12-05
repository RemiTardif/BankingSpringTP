package com.example.banking.domain.exception;

public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(String id) {
        super("Action avec l'id '" + id + "' non trouvée");
    }

    public StockNotFoundException(String symbol, boolean bySymbol) {
        super("Action avec le symbole '" + symbol + "' non trouvée");
    }
}
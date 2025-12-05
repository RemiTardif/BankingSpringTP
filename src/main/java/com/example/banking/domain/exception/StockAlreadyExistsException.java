package com.example.banking.domain.exception;

public class StockAlreadyExistsException extends RuntimeException {

    public StockAlreadyExistsException(String symbol) {
        super("Une action avec le symbole '" + symbol + "' existe déjà");
    }
}
package com.example.banking.domain.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String accountId) {
        super("Compte avec l'id '" + accountId + "' non trouvé");
    }
}
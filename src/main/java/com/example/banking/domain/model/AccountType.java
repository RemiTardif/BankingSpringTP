package com.example.banking.domain.model;

public enum AccountType {
    CHECKING("Compte Courant"),
    SAVINGS("Livret A"),
    PEA("Plan d'Épargne en Actions"),
    BUSINESS("Compte Professionnel");

    private final String displayName;

    AccountType(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
}
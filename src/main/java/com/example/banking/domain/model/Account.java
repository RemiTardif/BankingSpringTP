package com.example.banking.domain.model;

import com.github.f4b6a3.ulid.UlidCreator;

import java.math.BigDecimal;

public record Account(
        String id,
        String clientId,
        BigDecimal solde,     // Solde du compte
        AccountType type,
        String name
) {

    //Constructeur compact pour validation
    public Account {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'id ne peut pas être vide");
        }
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("Le clientId ne peut pas être vide");
        }
        if (solde == null) {
            throw new IllegalArgumentException("Le solde ne peut pas être null");
        }
        if (type == null) {
            throw new IllegalArgumentException("Le type de compte ne peut pas être null");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom du compte ne peut pas être vide");
        }
    }

    // Méthode pour créer un nouveau client avec ULID auto-généré
    public static Account create(String clientId, BigDecimal solde, AccountType type, String name) {
        return new Account(
                UlidCreator.getUlid().toString(),
                clientId,
                solde,
                type,
                name
        );
    }
    // Méthode pour créer un nouveau client avec un solde initial de 0
    public static Account createEmpty(String clientId, AccountType type, String name) {
        return create(clientId, BigDecimal.ZERO, type, name);
    }
}
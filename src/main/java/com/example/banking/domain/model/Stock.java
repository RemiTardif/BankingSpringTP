package com.example.banking.domain.model;

import com.github.f4b6a3.ulid.UlidCreator;

//Modèle métier représentant une action boursière
public record Stock(
        String id,
        String symbol,
        String name,
        double price,
        long lastUpdate
) {

    //Constructeur compact avec validation
    public Stock {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'ID ne peut pas être vide");
        }
        if (symbol == null || symbol.isBlank()) {
            throw new IllegalArgumentException("Le symbole ne peut pas être vide");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        if (lastUpdate <= 0) {
            throw new IllegalArgumentException("La date de mise à jour est invalide");
        }
    }

    //créer un Stock avec génération automatique d'ID
    public static Stock create(String symbol, String name, double price) {
        String id = UlidCreator.getMonotonicUlid().toString();
        long now = System.currentTimeMillis();
        return new Stock(id, symbol, name, price, now);
    }
}
package com.example.banking.domain.model;

import com.github.f4b6a3.ulid.UlidCreator;

public record User(
        String id,
        String login,
        String password,
        String clientId
) {
    // Constructeur compact pour validation
    public User {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'ID ne peut pas être vide");
        }
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Le login ne peut pas être vide");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être vide");
        }
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("Le clientId ne peut pas être vide");
        }
    }
    // Méthode pour créer un nouveau user avec ULID auto-généré
    public static User create(String login, String password, String clientId) {
        String id = UlidCreator.getMonotonicUlid().toString();
        return new User(id, login, password, clientId);
    }
}
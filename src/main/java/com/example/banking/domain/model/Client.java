package com.example.banking.domain.model;
import com.github.f4b6a3.ulid.UlidCreator;

public record Client(
        String id,
        String firstName,
        String lastName
) {
    // Constructeur compact pour validation
    public Client {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'id ne peut pas être null ou vide");
        }
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("Le prénom ne peut pas être null ou vide");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Le nom de famille ne peut pas être null ou vide");
        }
    }

    // Méthode pour créer un nouveau client avec ULID auto-généré
    public static Client create(String firstName, String lastName) {
        return new Client(UlidCreator.getUlid().toString(), firstName, lastName);
    }
}
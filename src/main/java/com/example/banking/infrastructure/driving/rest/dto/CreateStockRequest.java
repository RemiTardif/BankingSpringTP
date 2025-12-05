package com.example.banking.infrastructure.driving.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

//DTO pour la requête de création d'une action boursière
@Schema(description = "Requête de création d'une action boursière")
public record CreateStockRequest(

        @NotBlank(message = "Le symbole est obligatoire")
        @Schema(description = "Symbole boursier", example = "AAPL", required = true)
        String symbol,

        @NotBlank(message = "Le nom est obligatoire")
        @Schema(description = "Nom de l'entreprise", example = "Apple Inc.", required = true)
        String name,

        @Positive(message = "Le prix doit être positif")
        @Schema(description = "Prix de l'action en euros", example = "150.50", required = true)
        double price
) {}
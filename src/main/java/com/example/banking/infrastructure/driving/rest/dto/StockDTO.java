package com.example.banking.infrastructure.driving.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

//DTO pour représenter une action boursière
@Schema(description = "Action boursière")
public record StockDTO(

        @Schema(description = "ID unique de l'action", example = "01KB2668Y8X9F4J48Y5E3M5EY8")
        String id,

        @Schema(description = "Symbole boursier", example = "AAPL")
        String symbol,

        @Schema(description = "Nom de l'entreprise", example = "Apple Inc.")
        String name,

        @Schema(description = "Prix de l'action en euros", example = "150.50")
        double price,

        @Schema(description = "Date de dernière mise à jour (timestamp)", example = "1733324400000")
        long lastUpdate
) {}
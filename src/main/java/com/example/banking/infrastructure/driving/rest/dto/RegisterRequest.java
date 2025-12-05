package com.example.banking.infrastructure.driving.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

//DTO pour la requête d'inscription
@Schema(description = "Requête d'inscription d'un nouvel utilisateur")
public record RegisterRequest(

        @NotBlank(message = "Le login est obligatoire")
        @Schema(description = "Login unique de l'utilisateur", example = "john.doe", required = true)
        String login,

        @NotBlank(message = "Le mot de passe est obligatoire")
        @Schema(description = "Mot de passe de l'utilisateur", example = "monMotDePasse123", required = true)
        String password,

        @NotBlank(message = "Le clientId est obligatoire")
        @Schema(description = "ID du client à associer", example = "01KB2668Y8X9F4J48Y5E3M5EY8", required = true)
        String clientId
) {}
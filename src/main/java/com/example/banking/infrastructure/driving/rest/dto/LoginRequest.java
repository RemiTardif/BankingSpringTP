package com.example.banking.infrastructure.driving.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO pour la requête de connexion
 */
@Schema(description = "Requête de connexion d'un utilisateur")
public record LoginRequest(

        @NotBlank(message = "Le login est obligatoire")
        @Schema(description = "Login de l'utilisateur", example = "john.doe", required = true)
        String login,

        @NotBlank(message = "Le mot de passe est obligatoire")
        @Schema(description = "Mot de passe de l'utilisateur", example = "monMotDePasse123", required = true)
        String password
) {}
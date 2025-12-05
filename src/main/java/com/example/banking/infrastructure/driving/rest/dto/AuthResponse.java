package com.example.banking.infrastructure.driving.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

//DTO pour la réponse après authentification
@Schema(description = "Réponse d'authentification contenant le token JWT")
public record AuthResponse(

        @Schema(description = "Token JWT généré", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huLmRvZSJ9...")
        String token
) {}
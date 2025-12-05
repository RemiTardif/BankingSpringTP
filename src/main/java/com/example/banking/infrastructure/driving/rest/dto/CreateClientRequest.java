package com.example.banking.infrastructure.driving.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Requête de création d'un client")
public record CreateClientRequest(

        @Schema(description = "Prénom du client", example = "Jean")
        @NotBlank(message = "Le prénom est obligatoire")
        String firstName,

        @Schema(description = "Nom de famille du client", example = "Dupont")
        @NotBlank(message = "Le nom est obligatoire")
        String lastName
) {}
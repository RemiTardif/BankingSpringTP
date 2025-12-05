package com.example.banking.infrastructure.driving.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Représentation d'un client")
public record ClientDTO(

        @Schema(description = "Identifiant unique", example = "01ARZ3NDEKTSV4RRFFQ69G5FAV")
        String id,

        @Schema(description = "Prénom du client", example = "Jean")
        String firstName,

        @Schema(description = "Nom de famille du client", example = "Dupont")
        String lastName
) {}
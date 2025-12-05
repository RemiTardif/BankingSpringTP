package com.example.banking.infrastructure.driving.rest.dto;

import com.example.banking.domain.model.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

//DTO pour la création d'un compte bancaire
@Schema(description = "Requête de création d'un compte bancaire")
public record CreateAccountRequest(

        @NotBlank(message = "L'ID du client est obligatoire")
        @Schema(description = "ID du client propriétaire", example = "01ABC123XYZ456DEF789GHI012", required = true)
        String clientId,

        @NotNull(message = "Le solde initial est obligatoire")
        @PositiveOrZero(message = "Le solde ne peut pas être négatif")
        @Schema(description = "Solde initial du compte", example = "1000.00", required = true)
        BigDecimal solde,

        @NotNull(message = "Le type de compte est obligatoire")
        @Schema(description = "Type de compte", example = "CHECKING", required = true)
        AccountType type,

        @NotBlank(message = "Le nom du compte est obligatoire")
        @Schema(description = "Nom du compte", example = "Compte Courant", required = true)
        String name
) {}
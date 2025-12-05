package com.example.banking.infrastructure.driving.rest.dto;

import com.example.banking.domain.model.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

//DTO pour représenter un compte bancaire dans l'API REST
@Schema(description = "Représentation d'un compte bancaire")
public record AccountDTO(

        @Schema(description = "Identifiant unique du compte (ULID)", example = "01JBH8X9Z2KPRFT67YWGQ5N3M8")
        String id,

        @Schema(description = "Identifiant du client propriétaire", example = "01ABC123XYZ456DEF789GHI012")
        String clientId,

        @Schema(description = "Solde du compte", example = "1500.50")
        BigDecimal solde,

        @Schema(description = "Type de compte", example = "CHECKING")
        AccountType type,

        @Schema(description = "Nom du compte", example = "Compte Courant Principal")
        String name
) {}
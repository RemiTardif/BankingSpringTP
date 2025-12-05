package com.example.banking.infrastructure.driving.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Réponse d'erreur standard")
public record ErrorResponse(

        @Schema(description = "Code HTTP", example = "400")
        int status,

        @Schema(description = "Type d'erreur", example = "Bad Request")
        String error,

        @Schema(description = "Message d'erreur", example = "Le prénom est obligatoire")
        String message,

        @Schema(description = "Horodatage de l'erreur", example = "2025-01-25 16:30:00")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp
) {
    public ErrorResponse(int status, String error, String message) {
        this(status, error, message, LocalDateTime.now());
    }
}
package com.example.banking.domain.exception;

/**
 * Exception levée quand les identifiants de connexion sont invalides
 */
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("Identifiants invalides");
    }
}
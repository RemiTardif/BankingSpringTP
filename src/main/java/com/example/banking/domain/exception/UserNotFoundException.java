package com.example.banking.domain.exception;

/**
 * Exception levée quand un user n'est pas trouvé
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String login) {
        super("Utilisateur avec le login '" + login + "' non trouvé");
    }
}
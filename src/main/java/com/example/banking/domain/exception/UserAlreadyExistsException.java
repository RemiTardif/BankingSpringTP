package com.example.banking.domain.exception;

/**
 * Exception levée quand on essaie de créer un user avec un login déjà existant
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String login) {
        super("Un utilisateur avec le login '" + login + "' existe déjà");
    }
}
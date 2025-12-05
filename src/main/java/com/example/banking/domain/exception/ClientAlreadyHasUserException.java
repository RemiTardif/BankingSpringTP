package com.example.banking.domain.exception;

/**
 * Exception levée quand on essaie de créer un user pour un client qui en a déjà un
 * Relation 1-1 : Un client = Un user maximum
 */
public class ClientAlreadyHasUserException extends RuntimeException {

    public ClientAlreadyHasUserException(String clientId) {
        super("Le client avec l'id '" + clientId + "' a déjà un utilisateur associé");
    }
}
package com.example.banking.domain.exception;

/**
 * Exception levée quand on cherche un client qui n'existe pas
 */
public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String id) {
        super("Client avec l'id '" + id + "' non trouvé");
    }
}
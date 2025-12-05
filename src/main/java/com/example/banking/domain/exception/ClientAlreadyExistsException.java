package com.example.banking.domain.exception;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException(String firstName, String lastName) {
        super(String.format("Un client avec ce nom existe déjà"));
    }
    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
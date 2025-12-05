package com.example.banking.domain.usecase;

import com.example.banking.domain.exception.UserNotFoundException;
import com.example.banking.domain.model.User;
import com.example.banking.domain.port.UserRepository;

//Use case : Trouver un utilisateur par son login
public class FindUser {

    private final UserRepository userRepository;

    public FindUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

// Trouve un utilisateur par son login
    public User execute(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException(login));
    }
}
package com.example.banking.domain.usecase;

import com.example.banking.domain.exception.ClientAlreadyHasUserException;
import com.example.banking.domain.exception.ClientNotFoundException;
import com.example.banking.domain.exception.UserAlreadyExistsException;
import com.example.banking.domain.model.User;
import com.example.banking.domain.port.ClientRepository;
import com.example.banking.domain.port.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

//Use case : Créer un nouvel utilisateur
public class CreateUser {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUser(
            UserRepository userRepository,
            ClientRepository clientRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }


//Crée un nouvel utilisateur
    public User execute(String login, String password, String clientId) {
        // Vérification 1 : Le login doit être unique
        if (userRepository.existsByLogin(login)) {
            throw new UserAlreadyExistsException(login);
        }

        // Vérification 2 : Le client doit exister
        if (!clientRepository.existsById(clientId)) {
            throw new ClientNotFoundException(clientId);
        }

        // Vérification 3 : Le client ne doit pas déjà avoir un user (relation 1-1)
        if (userRepository.existsByClientId(clientId)) {
            throw new ClientAlreadyHasUserException(clientId);
        }

        // Hash du mot de passe avec BCrypt
        String hashedPassword = passwordEncoder.encode(password);

        // Création du User avec mot de passe hashé
        User user = User.create(login, hashedPassword, clientId);

        // Sauvegarde en base
        return userRepository.save(user);
    }
}
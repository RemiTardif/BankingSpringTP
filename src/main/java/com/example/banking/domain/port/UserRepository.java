package com.example.banking.domain.port;

import com.example.banking.domain.model.User;

import java.util.Optional;


public interface UserRepository {

    //Trouve un utilisateur par son login
    Optional<User> findByLogin(String login);

    //Sauvegarde un utilisateur
    User save(User user);

    //Vérifie si un login existe déjà
    boolean existsByLogin(String login);

    //Vérifie si un client a déjà un user
    boolean existsByClientId(String clientId);
}
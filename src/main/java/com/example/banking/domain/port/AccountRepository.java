package com.example.banking.domain.port;

import com.example.banking.domain.model.Account;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    List<Account> findAll();

    //Récupère un compte par son ID
    Optional<Account> findById(String id);

    //Récupère tous les comptes d'un client
    List<Account> findByClientId(String clientId);

    //Sauvegarde un compte
    Account save(Account account);

    //Vérifie si un compte existe
    boolean existsById(String id);
}
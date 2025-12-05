package com.example.banking.domain.usecase;

import com.example.banking.domain.exception.ClientNotFoundException;
import com.example.banking.domain.model.Account;
import com.example.banking.domain.model.AccountType;
import com.example.banking.domain.port.AccountRepository;
import com.example.banking.domain.port.ClientRepository;

import java.math.BigDecimal;

//Use case : Créer un compte bancaire
public class CreateAccount {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public CreateAccount(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

//Crée un nouveau compte pour un client existant
    public Account execute(String clientId, BigDecimal initalSolde, AccountType type, String name) {
        // Règle métier : vérifier que le client existe
        if (!clientRepository.existsById(clientId)) {
            throw new ClientNotFoundException(clientId);
        }

        // Créer le compte avec ULID auto-généré
        Account newAccount = Account.create(clientId, initalSolde, type, name);

        // Sauvegarder et retourner
        return accountRepository.save(newAccount);
    }
}
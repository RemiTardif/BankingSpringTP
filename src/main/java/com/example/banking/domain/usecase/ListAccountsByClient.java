package com.example.banking.domain.usecase;

import com.example.banking.domain.exception.ClientNotFoundException;
import com.example.banking.domain.model.Account;
import com.example.banking.domain.port.AccountRepository;
import com.example.banking.domain.port.ClientRepository;

import java.util.List;


public class ListAccountsByClient {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public ListAccountsByClient(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    //Liste tous les comptes d'un client
    public List<Account> execute(String clientId) {
        // Vérifier que le client existe
        if (!clientRepository.existsById(clientId)) {
            throw new ClientNotFoundException(clientId);
        }

        // Retourner tous les comptes du client
        return accountRepository.findByClientId(clientId);
    }
}
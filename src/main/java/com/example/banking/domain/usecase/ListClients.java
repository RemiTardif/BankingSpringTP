package com.example.banking.domain.usecase;

import com.example.banking.domain.model.Client;
import com.example.banking.domain.port.ClientRepository;
import java.util.List;

public class ListClients {

    private final ClientRepository clientRepository;

    // Injection de dépendance : on donne le repository au use case
    public ListClients(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    //Exécute le use case : retourne tous les clients
    public List<Client> execute() {
        return clientRepository.findAll();
    }
}
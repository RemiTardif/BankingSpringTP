package com.example.banking.domain.usecase;

import com.example.banking.domain.model.Client;
import com.example.banking.domain.port.ClientRepository;

public class CreateClient {

    private final ClientRepository clientRepository;

    // Injection de dépendance
    public CreateClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //Exécute le use case : crée un nouveau client
    public Client execute(String firstName, String lastName) {
        //Vérifie si le client existe déjà
        if (clientRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            throw new IllegalArgumentException("Ce client existe deja");
        }
        //Crée le nouveau client avec ULID
        Client newClient = Client.create(firstName, lastName);

        //Return le client
        return clientRepository.save(newClient);
    }
}
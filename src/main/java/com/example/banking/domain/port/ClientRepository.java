package com.example.banking.domain.port;

import com.example.banking.domain.model.Client;
import java.util.List;

public interface ClientRepository {
    List<Client> findAll();
    Client save(Client client);
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
    boolean existsById(String id);
}
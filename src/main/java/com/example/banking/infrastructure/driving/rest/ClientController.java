package com.example.banking.infrastructure.driving.rest;

import com.example.banking.domain.model.Client;
import com.example.banking.domain.usecase.CreateClient;
import com.example.banking.domain.usecase.ListClients;
import com.example.banking.infrastructure.driving.rest.dto.ClientDTO;
import com.example.banking.infrastructure.driving.rest.dto.CreateClientRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    private final ListClients listClients;
    private final CreateClient createClient;

    public ClientController(ListClients listClients, CreateClient createClient) {
        this.listClients = listClients;
        this.createClient = createClient;
    }

    @GetMapping
    @Operation(summary = "Liste tous les clients")
    @ApiResponse(responseCode = "200", description = "Liste des clients")
    public List<ClientDTO> findAll() {
        List<Client> clients = listClients.execute();

        return clients.stream()
                .map(client -> new ClientDTO(
                        client.id(),
                        client.firstName(),
                        client.lastName()
                ))
                .toList();
    }
    //Crée un nouveau client
    @PostMapping
    @Operation(summary = "Créer un client")
    @ApiResponse(responseCode = "201", description = "Client créé")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "409", description = "Client existe déjà")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO create(@Valid @RequestBody CreateClientRequest request) {
        Client createdClient = createClient.execute(
                request.firstName(),
                request.lastName()
        );

        return new ClientDTO(
                createdClient.id(),
                createdClient.firstName(),
                createdClient.lastName()
        );
    }
}
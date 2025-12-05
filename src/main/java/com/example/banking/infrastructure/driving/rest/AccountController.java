package com.example.banking.infrastructure.driving.rest;

import com.example.banking.domain.model.Account;
import com.example.banking.domain.usecase.CreateAccount;
import com.example.banking.domain.usecase.ListAccountsByClient;
import com.example.banking.infrastructure.driving.rest.dto.AccountDTO;
import com.example.banking.infrastructure.driving.rest.dto.CreateAccountRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Accounts", description = "Gestion des comptes bancaires")
public class AccountController {

    private final CreateAccount createAccount;
    private final ListAccountsByClient listAccountsByClient;

    public AccountController(
            CreateAccount createAccount,
            ListAccountsByClient listAccountsByClient) {
        this.createAccount = createAccount;
        this.listAccountsByClient = listAccountsByClient;
    }

    //Liste tous les comptes d'un client
    @GetMapping("/client/{clientId}")
    @Operation(summary = "Lister les comptes d'un client")
    @ApiResponse(responseCode = "200", description = "Liste des comptes du client")
    @ApiResponse(responseCode = "404", description = "Client non trouvé")
    public List<AccountDTO> getAccountsByClient(@PathVariable String clientId) {
        List<Account> accounts = listAccountsByClient.execute(clientId);

        return accounts.stream()
                .map(account -> new AccountDTO(
                        account.id(),
                        account.clientId(),
                        account.solde(),
                        account.type(),
                        account.name()
                ))
                .toList();
    }

    //Crée un nouveau compte
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un compte bancaire")
    @ApiResponse(responseCode = "201", description = "Compte créé")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "404", description = "Client non trouvé")
    public AccountDTO createNewAccount(@Valid @RequestBody CreateAccountRequest request) {
        Account createdAccount = createAccount.execute(
                request.clientId(),
                request.solde(),
                request.type(),
                request.name()
        );
        return new AccountDTO(
                createdAccount.id(),
                createdAccount.clientId(),
                createdAccount.solde(),
                createdAccount.type(),
                createdAccount.name()
        );
    }
}
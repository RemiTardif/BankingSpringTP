package com.example.banking.infrastructure.driven.jpa;

import com.example.banking.domain.model.AccountType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    //Clé primaire de la table
    @Id
    @Column(name = "id", length = 26)
    private String id;

    //ID du client propriétaire
    @Column(name = "client_id", length = 26, nullable = false)
    private String clientId;

    //Solde du compte
    @Column(name = "solde", nullable = false, precision = 19, scale = 2)
    private BigDecimal solde;

    //Type de compte (CHECKING, SAVINGS, etc.)
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 50, nullable = false)
    private AccountType type;

    //Nom du compt
    @Column(name = "name", length = 100, nullable = false)
    private String name;


    //Constructeur vide pour JPA
    public AccountEntity() {
    }

    //Constructeur avec tous les paramètres
    public AccountEntity(String id, String clientId, BigDecimal solde, AccountType type, String name) {
        this.id = id;
        this.clientId = clientId;
        this.solde = solde;
        this.type = type;
        this.name = name;
    }


    //Getters et Setters

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getSolde() {
        return solde;
    }
    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public AccountType getType() {
        return type;
    }
    public void setType(AccountType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
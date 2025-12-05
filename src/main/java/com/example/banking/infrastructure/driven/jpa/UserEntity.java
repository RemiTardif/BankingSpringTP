package com.example.banking.infrastructure.driven.jpa;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id", length = 26, nullable = false)
    private String id;

    @Column(name = "login", length = 50, nullable = false, unique = true)
    private String login;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "client_id", length = 26, nullable = false, unique = true)
    private String clientId;

    // Pas de rôles pour l'instant (on simplifie le TP6)
    // Si besoin plus tard : @Column(name = "role", length = 20)
    // private String role;


    public UserEntity() {
    }

    public UserEntity(String id, String login, String password, String clientId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
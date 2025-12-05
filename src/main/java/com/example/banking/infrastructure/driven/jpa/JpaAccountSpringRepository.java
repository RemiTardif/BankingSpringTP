package com.example.banking.infrastructure.driven.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Interface Spring Data JPA pour l'accès aux comptes
public interface JpaAccountSpringRepository extends JpaRepository<AccountEntity, String> {
    List<AccountEntity> findByClientId(String clientId);
}
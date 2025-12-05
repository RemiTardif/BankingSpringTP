package com.example.banking.infrastructure.driven.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserSpringRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByLogin(String login);

    boolean existsByLogin(String login);

    boolean existsByClientId(String clientId);
}
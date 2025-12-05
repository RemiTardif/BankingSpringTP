package com.example.banking.infrastructure.driven.jpa;

import com.example.banking.domain.model.Account;
import com.example.banking.domain.port.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaAccountRepository implements AccountRepository {

    // Injection de l'interface Spring Data JPA
    private final JpaAccountSpringRepository jpaAccountSpringRepository;

    public JpaAccountRepository(JpaAccountSpringRepository jpaAccountSpringRepository) {
        this.jpaAccountSpringRepository = jpaAccountSpringRepository;
    }

    //Récupère tous les comptes
    @Override
    public List<Account> findAll() {
        return jpaAccountSpringRepository.findAll().stream()
                .map(this::toDomain)  // Convertit chaque AccountEntity en Account
                .collect(Collectors.toList());
    }

    //Récupère un compte par son ID
    @Override
    public Optional<Account> findById(String id) {
        return jpaAccountSpringRepository.findById(id)
                .map(this::toDomain);
    }

    //Récupère tous les comptes d'un client
    @Override
    public List<Account> findByClientId(String clientId) {
        return jpaAccountSpringRepository.findByClientId(clientId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    //Sauvegarde un compte
    @Override
    public Account save(Account account) {
        AccountEntity entity = toEntity(account);
        AccountEntity saved = jpaAccountSpringRepository.save(entity);  // Sauvegarde
        return toDomain(saved);
    }

    //Vérifie si un compte existe
    @Override
    public boolean existsById(String id) {
        return jpaAccountSpringRepository.existsById(id);
    }

    //Convertit AccountEntity (infrastructure) → Account (domain)
    private Account toDomain(AccountEntity entity) {
        return new Account(
                entity.getId(),
                entity.getClientId(),
                entity.getSolde(),
                entity.getType(),
                entity.getName()
        );
    }

    //Convertit Account (domain) → AccountEntity (infrastructure)
    private AccountEntity toEntity(Account account) {
        return new AccountEntity(
                account.id(),
                account.clientId(),
                account.solde(),
                account.type(),
                account.name()
        );
    }
}
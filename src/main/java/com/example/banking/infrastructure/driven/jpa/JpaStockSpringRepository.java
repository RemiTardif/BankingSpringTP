package com.example.banking.infrastructure.driven.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Repository Spring Data JPA pour StockEntity
public interface JpaStockSpringRepository extends JpaRepository<StockEntity, String> {

    Optional<StockEntity> findBySymbol(String symbol);


    boolean existsBySymbol(String symbol);
}
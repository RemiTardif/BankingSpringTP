package com.example.banking.infrastructure.driven.jpa;

import com.example.banking.domain.model.Stock;
import com.example.banking.domain.port.StockRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adapter JPA pour StockRepository
 *
 * Pont entre le domain (Stock record) et l'infrastructure (StockEntity JPA)
 */
@Repository
public class JpaStockRepository implements StockRepository {

    private final JpaStockSpringRepository jpaStockSpringRepository;

    public JpaStockRepository(JpaStockSpringRepository jpaStockSpringRepository) {
        this.jpaStockSpringRepository = jpaStockSpringRepository;
    }

    @Override
    public List<Stock> findAll() {
        return jpaStockSpringRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Stock> findById(String id) {
        return jpaStockSpringRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Stock> findBySymbol(String symbol) {
        return jpaStockSpringRepository.findBySymbol(symbol)
                .map(this::toDomain);
    }

    @Override
    public Stock save(Stock stock) {
        StockEntity entity = toEntity(stock);
        StockEntity savedEntity = jpaStockSpringRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public boolean existsBySymbol(String symbol) {
        return jpaStockSpringRepository.existsBySymbol(symbol);
    }


    // ==================== Conversions ====================

    /**
     * Convertit une StockEntity (JPA) en Stock (domain)
     */
    private Stock toDomain(StockEntity entity) {
        return new Stock(
                entity.getId(),
                entity.getSymbol(),
                entity.getName(),
                entity.getPrice(),
                entity.getLastUpdate()
        );
    }

    /**
     * Convertit un Stock (domain) en StockEntity (JPA)
     */
    private StockEntity toEntity(Stock stock) {
        return new StockEntity(
                stock.id(),
                stock.symbol(),
                stock.name(),
                stock.price(),
                stock.lastUpdate()
        );
    }
}
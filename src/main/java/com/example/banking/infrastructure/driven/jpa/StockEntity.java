package com.example.banking.infrastructure.driven.jpa;

import jakarta.persistence.*;

//Entité JPA représentant une action boursière
@Entity
@Table(name = "stocks")
public class StockEntity {

    @Id
    @Column(name = "id", length = 26, nullable = false)
    private String id;

    @Column(name = "symbol", length = 10, nullable = false, unique = true)
    private String symbol;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "last_update", nullable = false)
    private long lastUpdate;


    // ==================== Constructeurs ====================

//Constructeur vide OBLIGATOIRE pour JPA
    public StockEntity() {
    }

    public StockEntity(String id, String symbol, String name, double price, long lastUpdate) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.lastUpdate = lastUpdate;
    }


    // ==================== Getters / Setters ====================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
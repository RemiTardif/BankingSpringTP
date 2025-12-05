package com.example.banking.infrastructure.driving.rest;

import com.example.banking.domain.model.Stock;
import com.example.banking.domain.usecase.CreateStock;
import com.example.banking.domain.usecase.ListStocks;
import com.example.banking.infrastructure.driving.rest.dto.CreateStockRequest;
import com.example.banking.infrastructure.driving.rest.dto.StockDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Contrôleur REST pour la gestion des actions boursières
@RestController
@RequestMapping("/api/stocks")
@Tag(name = "Stocks", description = "Gestion des actions boursières")
public class StockController {

    private final ListStocks listStocks;
    private final CreateStock createStock;

    public StockController(ListStocks listStocks, CreateStock createStock) {
        this.listStocks = listStocks;
        this.createStock = createStock;
    }
    //Liste toutes les actions boursières
    @GetMapping
    @Operation(summary = "Lister toutes les actions boursières")
    @ApiResponse(responseCode = "200", description = "Liste des actions")
    public List<StockDTO> getAllStocks() {
        List<Stock> stocks = listStocks.execute();

        return stocks.stream()
                .map(stock -> new StockDTO(
                        stock.id(),
                        stock.symbol(),
                        stock.name(),
                        stock.price(),
                        stock.lastUpdate()
                ))
                .toList();
    }

    //Crée une nouvelle action boursière
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer une nouvelle action boursière")
    @ApiResponse(responseCode = "201", description = "Action créée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "409", description = "Symbole déjà existant")
    public StockDTO createNewStock(@Valid @RequestBody CreateStockRequest request) {
        Stock createdStock = createStock.execute(
                request.symbol(),
                request.name(),
                request.price()
        );

        return new StockDTO(
                createdStock.id(),
                createdStock.symbol(),
                createdStock.name(),
                createdStock.price(),
                createdStock.lastUpdate()
        );
    }
}
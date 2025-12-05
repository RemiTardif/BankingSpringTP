package com.example.banking.infrastructure.application.config;

import com.example.banking.domain.port.AccountRepository;
import com.example.banking.domain.port.ClientRepository;
import com.example.banking.domain.port.StockRepository;
import com.example.banking.domain.port.UserRepository;
import com.example.banking.domain.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseCaseConfiguration {

    // ==================== Use Cases Client ====================

    @Bean
    public ListClients listClients(ClientRepository clientRepository) {
        return new ListClients(clientRepository);
    }

    @Bean
    public CreateClient createClient(ClientRepository clientRepository) {
        return new CreateClient(clientRepository);
    }


    // ==================== Use Cases Account ====================

    @Bean
    public ListAccountsByClient listAccountsByClient(
            AccountRepository accountRepository,
            ClientRepository clientRepository) {
        return new ListAccountsByClient(accountRepository, clientRepository);
    }

    @Bean
    public CreateAccount createAccount(
            AccountRepository accountRepository,
            ClientRepository clientRepository) {
        return new CreateAccount(accountRepository, clientRepository);
    }


    // ==================== Use Cases User (TP6) ====================

    @Bean
    public CreateUser createUser(
            UserRepository userRepository,
            ClientRepository clientRepository,
            PasswordEncoder passwordEncoder) {
        return new CreateUser(userRepository, clientRepository, passwordEncoder);
    }

    @Bean
    public FindUser findUser(UserRepository userRepository) {
        return new FindUser(userRepository);
    }


    // ==================== Security ====================

    /**
     * Bean pour encoder les mots de passe avec BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CreateStock createStock(StockRepository stockRepository) {
        return new CreateStock(stockRepository);
    }

    @Bean
    public ListStocks listStocks(StockRepository stockRepository) {
        return new ListStocks(stockRepository);
    }
}
package com.example.banking.infrastructure.driving.web;

import com.example.banking.domain.model.Client;
import com.example.banking.domain.usecase.ListClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//Contrôleur web pour les pages Thymeleaf
@Controller
public class PageController {

    private final ListClients listClients;

    public PageController(ListClients listClients) {
        this.listClients = listClients;
    }

    // Page d'accueil : liste des clients
    @GetMapping("/")
    public String indexPage(Model model) {
        List<Client> clients = listClients.execute();
        model.addAttribute("clients", clients);
        return "index";
    }

    //Page des comptes d'un client
    @GetMapping("/clients/{id}")
    public String accountsPage(@PathVariable String id) {
        return "comptes";
    }
    //Page de connexion
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    //Page d'inscription
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/test")
    public String testPage() {
        return "test";
    }

    //Page des cours de bourse
    @GetMapping("/stocks")
    public String stocksPage() {
        return "stocks";
    }
}
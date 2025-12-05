package com.example.banking.infrastructure.driving.rest;

import com.example.banking.domain.exception.InvalidCredentialsException;
import com.example.banking.domain.model.User;
import com.example.banking.domain.service.JwtService;
import com.example.banking.domain.usecase.CreateUser;
import com.example.banking.domain.usecase.FindUser;
import com.example.banking.infrastructure.driving.rest.dto.AuthResponse;
import com.example.banking.infrastructure.driving.rest.dto.LoginRequest;
import com.example.banking.infrastructure.driving.rest.dto.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//Contrôleur REST pour l'authentification
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentification et inscription")
public class AuthController {

    private final CreateUser createUser;
    private final FindUser findUser;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(
            CreateUser createUser,
            FindUser findUser,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.createUser = createUser;
        this.findUser = findUser;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    //Inscription d'un nouvel utilisateur
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Inscription d'un nouvel utilisateur")
    @ApiResponse(responseCode = "201", description = "Utilisateur créé avec succès")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    @ApiResponse(responseCode = "404", description = "Client non trouvé")
    @ApiResponse(responseCode = "409", description = "Login déjà existant ou client déjà associé")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        // Crée le user (le use case gère toutes les validations et le hash du password)
        User user = createUser.execute(
                request.login(),
                request.password(),
                request.clientId()
        );

        // Génère un token JWT
        String token = jwtService.generateToken(user.login(), user.clientId());

        // Retourne le token
        return new AuthResponse(token);
    }

    //Connexion d'un utilisateur
    @PostMapping("/login")
    @Operation(summary = "Connexion d'un utilisateur")
    @ApiResponse(responseCode = "200", description = "Connexion réussie")
    @ApiResponse(responseCode = "401", description = "Identifiants invalides")
    @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        // Récupère le user par son login
        User user = findUser.execute(request.login());

        // Vérifie que le mot de passe correspond
        // passwordEncoder.matches() compare le password en clair avec le hash BCrypt
        if (!passwordEncoder.matches(request.password(), user.password())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(user.login(), user.clientId());
        return new AuthResponse(token);
    }
}
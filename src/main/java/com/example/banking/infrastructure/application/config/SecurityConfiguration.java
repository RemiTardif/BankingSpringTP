package com.example.banking.infrastructure.application.config;

import com.example.banking.infrastructure.driving.rest.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration de Spring Security avec JWT
 *
 * Routes publiques :
 * - POST /auth/register
 * - POST /auth/login
 * - GET /swagger-ui.html (et toutes les routes Swagger)
 * - GET / (page d'accueil Thymeleaf)
 *
 * Routes protégées (JWT requis) :
 * - /api/** (tous les endpoints API)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactive CSRF (pas besoin pour API REST avec JWT)
                .csrf(csrf -> csrf.disable())

                // Configuration des autorisations
                .authorizeHttpRequests(auth -> auth
                        // Routes publiques (pas d'authentification requise)
                        .requestMatchers("/auth/**").permitAll()              // /auth/register, /auth/login
                        .requestMatchers("/login").permitAll()                // ← Page login
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/stocks").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()        // Swagger UI
                        .requestMatchers("/v3/api-docs/**").permitAll()       // OpenAPI docs
                        .requestMatchers("/").permitAll()                     // Page d'accueil
                        .requestMatchers("/clients/**").permitAll()           // Pages Thymeleaf
                        .requestMatchers("/js/**", "/css/**").permitAll()     // Ressources statiques

                        // Routes protégées (JWT requis)
                        .requestMatchers("/api/**").permitAll()



                        // Toutes les autres routes : authentification requise
                        .anyRequest().authenticated()
                )

                // Désactive la gestion de session (stateless avec JWT)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Ajoute le filtre JWT AVANT le filtre d'authentification par défaut
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
package com.example.banking.infrastructure.driving.rest.security;

import com.example.banking.domain.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

// Filtre JWT qui intercepte chaque requête HTTP
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final JwtTokenService jwtTokenService;

    public JwtAuthenticationFilter(JwtService jwtService, JwtTokenService jwtTokenService) {
        this.jwtService = jwtService;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        //Extraire le token du header Authorization
        String token = jwtTokenService.extractTokenFromRequest(request);

        //Si token présent et valide
        if (token != null && jwtService.validateToken(token)) {

            //Extraire le login depuis le token
            String login = jwtService.getLoginFromToken(token);

            //Créer une authentification Spring Security
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            login,                          // Principal (login de l'utilisateur)
                            null,                           // Credentials (pas besoin)
                            Collections.emptyList()         // Authorities (pas de rôles pour l'instant)
                    );

            //Ajouter les détails de la requête
            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            //Enregistre l'authentification dans le contexte Spring Security
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}

package com.example.banking.infrastructure.driving.rest.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

//Service pour extraire le token JWT depuis les requêtes HTTP
@Service
public class JwtTokenService {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    public String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            return authHeader.substring(BEARER_PREFIX.length());
        }

        return null;
    }
}
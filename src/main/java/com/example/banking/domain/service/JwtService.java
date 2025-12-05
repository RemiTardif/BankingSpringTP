package com.example.banking.domain.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

//ervice pour la gestion des JWT (JSON Web Tokens)
@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long expirationTime;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expirationTime) {
        // Crée une clé secrète à partir du string
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationTime = expirationTime;
    }

    public String generateToken(String login, String clientId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(login)                     // Subject = login (identifiant principal)
                .claim("clientId", clientId)        // Claim custom : clientId
                .issuedAt(now)                      // Date de création
                .expiration(expiryDate)             // Date d'expiration
                .signWith(secretKey)                // Signature avec clé secrète
                .compact();
    }

//Extrait le login (subject) d'un token
    public String getLoginFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

   //Extrait le login (subject) d'un token
    public String getClientIdFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.get("clientId", String.class);
    }

   //Valide un token JWT
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//Extrait les claims (données) d'un token
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
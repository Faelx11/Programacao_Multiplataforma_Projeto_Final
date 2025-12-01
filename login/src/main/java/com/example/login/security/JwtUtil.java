package com.example.login.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utilitário para geração de tokens JWT.
 */
@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Gera um token JWT simples contendo o nome de usuário como subject.
     *
     * @param username nome do usuário
     * @return token JWT
     */
    public String generateToken(String username) {
        long now = System.currentTimeMillis();
        // Token válido por 1 hora
        Date expiryDate = new Date(now + 3600_000);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(now))
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }
}
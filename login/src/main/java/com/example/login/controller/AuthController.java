package com.example.login.controller;

import com.example.login.dto.LoginRequest;
import com.example.login.dto.LoginResponse;
import com.example.login.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint responsável pelo processo de autenticação.
 */
@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Autentica um usuário e retorna um token JWT simples.
     *
     * Para fins didáticos, este método valida usuário e senha de forma estática:
     * usuário: **admin**, senha: **password**.
     *
     * @param loginRequest corpo da requisição com username e password
     * @return token ou erro 401
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        // Verifica credenciais estáticas. Em uma aplicação real usar banco de dados/serviço.
        if ("admin".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}
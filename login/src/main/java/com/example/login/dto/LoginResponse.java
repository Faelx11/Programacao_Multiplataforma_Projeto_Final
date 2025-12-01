package com.example.login.dto;

/**
 * Resposta de login contendo o token de autenticação.
 */
public class LoginResponse {
    private final String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
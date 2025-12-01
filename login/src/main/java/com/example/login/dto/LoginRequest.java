package com.example.login.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Requisição de login recebendo usuário e senha.
 */
public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
package com.practicas.TicTacTareas.dtos.auth;

public class AuthResponseDTO {
    private final String jwt;
    public AuthResponseDTO(String jwt) { this.jwt = jwt; }
    public String getJwt() { return jwt; }
}
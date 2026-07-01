package com.montecarlo.service;

public interface JwtService {

    String generateToken(String correo, String rol);

    String extractUsername(String token);

    boolean isTokenValid(String token);
}
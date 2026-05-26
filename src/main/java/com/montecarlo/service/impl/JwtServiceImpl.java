package com.montecarlo.service.impl;

import com.montecarlo.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String SECRET_KEY =
            "montecarlo_secret_key_muy_segura_2026_montecarlo";

    @Override
    public String generateToken(String correo) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUsername(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(
                        Keys.hmacShaKeyFor(SECRET_KEY.getBytes())
                )
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public boolean isTokenValid(String token) {

        try {

            Jwts.parserBuilder()
                    .setSigningKey(
                            Keys.hmacShaKeyFor(SECRET_KEY.getBytes())
                    )
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
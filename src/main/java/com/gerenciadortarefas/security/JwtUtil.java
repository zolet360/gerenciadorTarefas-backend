package com.gerenciadortarefas.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    // 🔑 Chave secreta fixa em Base64 (pode gerar outra se quiser)
    private static final String SECRET_KEY = "NEE0MDRFNjM1MjY2NTU2QTU4NkUzMjcyMzU3NTM4NzgyRjQxM0Y0NDI4NDcyQjRCNjI1MDY1NTM2ODU2NkQ1OQ==";

    private final SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));

    // ⏰ Durações dos tokens
    private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 15; // 15 min
    private final long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24 * 7; // 7 dias

    // 🎫 Gera token de acesso
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ♻️ Gera token de refresh
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .claim("type", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Valida o token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            System.out.println("❌ Token inválido: " + e.getMessage());
            return false;
        }
    }

    // 📤 Extrai o username do token
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
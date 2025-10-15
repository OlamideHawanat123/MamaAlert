package com.mamaalert.security;

import com.mamaalert.data.model.Role;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;
import com.mamaalert.data.model.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // You can also load this from application.properties or environment variable
    private static final String SECRET = "X9sP4tY7qL2rM8vZ1xN5bA6uF3dH9kG7"; // at least 32 chars
    private static final long EXPIRATION_MS = 1000 * 60 * 60 * 24; // 24 hours

    private SecretKey key;

    @PostConstruct
    public void init() {
        // Create a secure key for HS256
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    // Generate JWT token with email and role claims
    public String generateToken(String email, Role role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract email (subject) from token
    public String extractEmail(String token) {
        return parseToken(token).getBody().getSubject();
    }

    // Extract role from token
    public String extractRole(String token) {
        return parseToken(token).getBody().get("role", String.class);
    }

    // Validate token for a given user
    public boolean validateToken(String token, String email) {
        try {
            Claims claims = parseToken(token).getBody();
            return claims.getSubject().equals(email) && !isTokenExpired(claims);
        } catch (JwtException e) {
            return false;
        }
    }

    // Check if token is expired
    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    // Parse the JWT token
    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}



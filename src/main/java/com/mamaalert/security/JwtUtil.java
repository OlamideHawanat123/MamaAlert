package com.mamaalert.security;

import com.mamaalert.data.model.Role;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;
//import com.mamaalert.data.model.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
//import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET = "X9sP4tY7qL2rM8vZ1xN5bA6uF3dH9kG7";
    private static final long EXPIRATION_MS = 1000 * 60 * 60 * 24;

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email, Role role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return parseToken(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return parseToken(token).getBody().get("role", String.class);
    }

    public boolean validateToken(String token, String email) {
        try {
            Claims claims = parseToken(token).getBody();
            return claims.getSubject().equals(email) && !isTokenExpired(claims);
        } catch (JwtException e) {
            return false;
        }
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }
}



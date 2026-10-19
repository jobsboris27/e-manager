package dev.sorokin.eventmanager.security.jwt;

import dev.sorokin.eventmanager.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

@Service
public class JwtService {
    private final SecretKey signingKey;
    private final long ttlMillis;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.lifetime}") Duration lifetime) {
        this.signingKey = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8));
        this.ttlMillis = lifetime.toMillis();
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.login())
                .claim("userId", user.id())
                .claim("role", user.role().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ttlMillis))
                .signWith(signingKey)
                .compact();
    }

    public String extractLogin(String token) {
        return parseClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return parseClaims(token).get("role", String.class);
    }

    public boolean isValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
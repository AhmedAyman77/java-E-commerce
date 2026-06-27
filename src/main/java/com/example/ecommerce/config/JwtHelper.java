package com.example.ecommerce.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {
    @Value("${jwt.secret}")
    private String secret;

    public String extractUsername(String token) {
        // Claims::getSubject == claims -> claims.getSubject() i pass this function to extractFromClaims and he will apply it
        return extractFromClaims(token, Claims::getSubject);
    }

    public Date extractTokenExpirationDate(String token) {
        return extractFromClaims(token, Claims::getExpiration);
    }

    public <T> T extractFromClaims(String token, Function<Claims, T> func) {
        Claims claim = extractClaims(token);

        return func.apply(claim);
    }

    private Claims extractClaims(String token) {
        return Jwts
                    .parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, UserDetails user) {
        String username = extractUsername(token);
        Date tokenExpirationDate = extractTokenExpirationDate(token);

        boolean isUserMatch = Objects.equals(username, user.getUsername());
        boolean isDateExpried = tokenExpirationDate.before(new Date(System.currentTimeMillis()));
        return isUserMatch && !isDateExpried;
    }

    public String generateToken(UserDetails userDetails) {
        return this.generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts
                    .builder()
                    .claims(claims)
                    .subject(userDetails.getUsername())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                    .signWith(getSignInKey())
                    .compact();
    }
}

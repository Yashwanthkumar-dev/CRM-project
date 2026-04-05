package com.example.crm.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class Util {
    private final String SECRET_KEY = "LLWeVsZAIkIzl/i7fB37X3nIUKGdjh8aawsBkendgsA=";
    private static final long EXPIRATION = 1000 * 60 * 60;

    public SecretKey getSignInkey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSignInkey())
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignInkey()).build().parseSignedClaims(token).getPayload();
    }

    public boolean valiedtoken(String token, String email) {
        try {
            Claims claims = extractAllClaims(token);
            String extractEmail = claims.getSubject();
            boolean isNotExpired = claims.getExpiration().after(new Date());
            return (extractEmail.equals(email) && isNotExpired);
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractEmail(String token){
        Claims claim = extractAllClaims(token);
        return claim.getSubject();
    }


}

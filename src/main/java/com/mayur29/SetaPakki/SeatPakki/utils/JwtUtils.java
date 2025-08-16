package com.mayur29.SetaPakki.SeatPakki.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private final String SECRET_KEY = "secretkey09876543210ASDFGHJKLMNO";
    public String createToken(String userName) {
        Map<String,Object> claims = new HashMap<>();
        return generateToken(claims,userName);
    }

    private String generateToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .claims(claims)
                .subject(userName)
                .header().empty().add("typ","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*5))
                .signWith(getSignKey())
                .compact();

    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUserName(String jwt) {
        return extractClaims(jwt).getSubject();
    }

    private Claims extractClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public boolean validateToken(String jwt) {
        return !extractClaims(jwt).getExpiration().before(new Date());
    }
}

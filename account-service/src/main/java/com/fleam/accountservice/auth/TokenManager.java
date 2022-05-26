package com.fleam.accountservice.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenManager {

    private static final String secretKey = "fleamsecretkey";
    private static final Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final int validity = 60*60*1000; // 1 hour


    public static String generateToken(String usename) {
        return Jwts.builder()
                .setSubject(usename)
                .setIssuer("fleam.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(signingKey)
                .compact();
    }


    public static boolean tokenValidate(String token){
        return getUsernameFromToken(token) != null && !isExpired(token);
    }

    public static String getUsernameFromToken(String token){
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public static boolean isExpired(String token){
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public static Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
    }
}

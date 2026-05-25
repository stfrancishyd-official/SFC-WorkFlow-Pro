package com.sfc.workflow.config;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sfc.workflow.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    @Value("${jwt.secrete}")
    private String SECRETE_KEY;

    public String generateToken(User user) {

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, SECRETE_KEY)
                .compact();
    }

    // extract username

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    // extract Expiration

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Generic Claim Extractor
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    // Extract All Claims
    private Claims extractAllClaims(String token) {

        return Jwts.parser()
                .setSigningKey(SECRETE_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // Validate Token
    public Boolean validateToken(String token, String email) {

        final String username = extractUsername(token);

        return (username.equals(email)
                &&
                !extractExpiration(token).before(new Date()));
    }

}

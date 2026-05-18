package com.sfc.workflow.config;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.sfc.workflow.entity.User;

import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
    
    private final String SECRETE_KEY;

     public String generateToken(User user){
        
        return jwt.builder()
                   .setSubject(user.getEmail())
                   .claim("role", user.getRole().name())
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //1 hour
                   .signWith(SignatureAlgorithm.HS256,SECRETE_KEY)
                   .compact();
     }
}

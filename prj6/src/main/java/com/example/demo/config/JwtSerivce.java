package com.example.demo.config;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtSerivce {

    private static final Strin SECRET_FINAL_KEY=""

    public String extractUsername(String jwtToken) {
        return null;
    }

    private Claims extractAllClaims(String token){
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private byte[] getSignInKey() {
        return null;
    }

}

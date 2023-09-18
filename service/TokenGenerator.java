package com.example.task.service;
import java.util.*;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
/**
 *
 * @author user
 */
@Component
public class TokenGenerator {

    public String generateToken(String username) {
        String secretKey = "0013"; // Replace with your own secret key
        long expirationTime = 86400000; // 1 day in milliseconds

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean verifyToken(String token, String secretKey) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            // Verify additional claims or expiration if needed.
            return true;
        } catch (SignatureException | MalformedJwtException e) {
            // Token is invalid
            return false;
        }
    }
}

package com.taskflow.task_scheduler_service.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtil {

    // Secret key used to sign and verify JWT tokens
    private final String secretKey = "your-super-secure-and-very-long-secret-key";


    public Claims extractClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("🔍 JWT DEBUG -> token: " + token);
            System.out.println("🔍 JWT DEBUG -> key: " + secretKey);
            throw new RuntimeException("JWT parse error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }


    // Extracts the claims from the JWT token (additional token information)
    //public Claims extractClaims(String token) {
      //  return Jwts.parser()
       //         .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))) // Sets the secret key to validate the token signature
        //        .build()
          //      .parseClaimsJws(token) // Parses the JWT token and obtains the claims
            //    .getBody(); // Returns the body of the claims
   // }

    // Extracts the email from the JWT token
    public String extractTokenEmail(String token) {
        // Gets the subject (username) from the token claims
        return extractClaims(token).getSubject();
    }

    // Checks if the JWT token is expired
    public boolean isTokenExpired(String token) {
        // Compares the token expiration date with the current date
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Validates the JWT token by checking the username and that the token is not expired
    public boolean validateToken(String token, String username) {
        // Extracts the username from the token
        final String extractedUsername = extractTokenEmail(token);
        // Checks if the username from the token matches the provided one and that the token is not expired
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}

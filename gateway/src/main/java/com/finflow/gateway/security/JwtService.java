package com.finflow.gateway.security;

import com.finflow.gateway.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;
    private final long jwtExpiration;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long jwtExpiration
    ) {
        this.secretKey = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
        this.jwtExpiration = jwtExpiration;
    }

    public String generateToken(UserDetails userDetails) {

        CustomUserDetails customUserDetails =
                (CustomUserDetails) userDetails;

        Date now = new Date();

        return Jwts.builder()
                .subject(
                        String.valueOf(customUserDetails.getUserId())
                )
                .claim(
                        "email",
                        customUserDetails.getEmail()
                )
                .claim(
                        "role",
                        customUserDetails.getAuthorities()
                                .iterator()
                                .next()
                                .getAuthority()
                                .replace("ROLE_", "")
                )
                .issuedAt(now)
                .expiration(
                        new Date(now.getTime() + jwtExpiration)
                )
                .signWith(secretKey)
                .compact();
    }

    public Long extractUserId(String token) {

        String subject = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

        return Long.valueOf(subject);
    }

    public boolean isTokenValid(String token) {

        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
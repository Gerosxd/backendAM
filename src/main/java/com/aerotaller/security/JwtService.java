package com.aerotaller.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService
{

    @Value("${app.jwt.secret}")
    private String secret;

    @Value("${app.jwt.expiration-ms}")
    private long expirationMs;

    private SecretKey key()
    {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username, Map<String, Object> extraClaims)
    {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .subject(username)
                .claims(extraClaims)
                .issuedAt(now)
                .expiration(exp)
                .signWith(key())
                .compact();
    }

    public String extractUsername(String token)
    {
        return parseClaims(token).getSubject();
    }

    public boolean isTokenValid(String token)
    {
        try
        {
            Claims c = parseClaims(token);
            return c.getExpiration().after(new Date());
        } catch (Exception e)
        {
            return false;
        }
    }

    private Claims parseClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
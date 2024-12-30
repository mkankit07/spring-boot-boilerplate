package com.usertracker.utils;

import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import com.usertracker.modal.request.LoginRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@UtilityClass
public class JwtHelper {
    @Setter
    private String jwtSecretKey;

    @Setter
    private Long jwtTokenExpiryTimeInMillis;

    public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(final String token) {

        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(final String token) {

        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(final String token) {

        return Jwts.parser()
            .verifyWith(getSignKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).after(new Date());
    }

    public Boolean validateToken(final String token, final UserDetails userDetails) {

        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && isTokenExpired(token);
    }

    public String generateToken(final LoginRequest loginRequest) {

        return createToken(new HashMap<>(), loginRequest);
    }

    public String createToken(final Map<String, Object> map, final LoginRequest loginRequest) {

        return Jwts.builder()
            .claims(map)
            .subject(loginRequest.getEmail())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + jwtTokenExpiryTimeInMillis))
            .signWith(getSignKey())
            .compact();
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
    }
}



package com.example.block7crudvalidation.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

public class TokenUtils {
    private final static String ACCESS_TOKEN_SECRET = "4qhq8LrEBfYcaRHxhdb9zURb2rf8e7Ud";
    private final static Long ACCES_TOKEN_VALIDITY_SECONS = 2_592_000L;

    public static String createToken(UserDetailsImpl userDetails, String usuario) {
        long expirationTime = ACCES_TOKEN_VALIDITY_SECONS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", userDetails.getNombre());

        var authorities = userDetails.getAuthorities();

        return Jwts.builder()
                .setSubject(usuario)
                .setExpiration(expirationDate)
                .claim("authorities", authorities.stream().map(GrantedAuthority::getAuthority).toList())
                .addClaims(extra)
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET.getBytes())
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try{
            Jws<Claims> jws= Jwts.parser().setSigningKey(ACCESS_TOKEN_SECRET.getBytes()).parseClaimsJws(token);
            String username = jws.getBody().getSubject();

            List<SimpleGrantedAuthority> authorities = null;
            Object authoritiesObject = jws.getBody().get("authorities");
            if (authoritiesObject != null) {
                authorities = ((List<String>) authoritiesObject).stream().map(SimpleGrantedAuthority::new).toList();
            }

            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

        } catch (JwtException e ) {
            throw new RuntimeException(e);
        }
    }
}

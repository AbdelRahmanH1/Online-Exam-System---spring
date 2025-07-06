package com.system.online_exam_system.auth.services;

import com.system.online_exam_system.auth.config.JwtConfig;
import com.system.online_exam_system.auth.entities.CustomUserDetails;
import com.system.online_exam_system.user.entites.User;
import com.system.online_exam_system.user.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {
    private final JwtConfig jwtConfig;

    public String generateToken(CustomUserDetails user) {

        return Jwts
                .builder()
                .subject(user.getId().toString())
                .claim("role",user.getRole())
                .claim("username",user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L * jwtConfig.getToken_expiration() ))
                .signWith(jwtConfig.getSecretKey())
                .compact();
    }

    public Claims claims(String token){
        return Jwts
                .parser()
                .verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token){
        try {
            var claims = claims(token);
            return claims.getExpiration().after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String token){
        return Long.valueOf(claims(token).getSubject());
    }
    public String getUsernameFromToken(String token){
        return claims(token).get("username",String.class);
    }
    public Role getRoleFromToken(String token){
        return Role.valueOf(claims(token).get("role",String.class));
    }
}

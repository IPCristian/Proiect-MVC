package com.project.proiectspring.security;

import com.project.proiectspring.model.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTService {

    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);

    @Value("${app.JWTSecretKey}")
    private String JWTSecretKey;

    @Value("${app.JWTExpiry}")
    private int JWTExpiry;

    public String generateJWTToken(Authentication authentication)
    {
        User user = (User) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWTExpiry);

        List<String> roleList = user.getAuthorities().stream().map(role ->
                role.getAuthority()
        ).collect(Collectors.toList());

        return Jwts.builder()
                .setId(String.valueOf(user.getId()))
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWTSecretKey)
                .claim("roles", roleList)
                .compact();
    }

    public Long getUserIdFromJWTToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWTSecretKey)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getId());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWTSecretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

}

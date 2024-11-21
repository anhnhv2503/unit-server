package com.anhnhv.unit.server.security.jwt;

import com.anhnhv.unit.server.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class JwtUtils {

    @Value("${unit.app.jwtSecret}")
    private String jwtSecret;
    @Value("${unit.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        List<String> role = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuer("unit")
                .claim("id", userPrincipal.getId())
                .claim("roles", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .setId(String.valueOf(UUID.randomUUID()))
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(jwt);
            return true;
        }catch (MalformedJwtException e){
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public String getUserNameFromJwtToken(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(key()).build().parseClaimsJws(jwt).getBody().getSubject();
    }
}

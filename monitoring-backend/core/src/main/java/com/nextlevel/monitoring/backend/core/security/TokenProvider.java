package com.nextlevel.monitoring.backend.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.nextlevel.monitoring.backend.config.Configuration;

@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";

    @Autowired
    private Configuration configuration;

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, configuration.getSecretKey())
                .setExpiration(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 5))).compact();
    }

    Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(configuration.getSecretKey()).parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new NliUsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    Jws<Claims> validateAndParseToken(String authToken) {
        return Jwts.parser().setSigningKey(configuration.getSecretKey()).parseClaimsJws(authToken);
    }

}
package com.nextlevel.monitoring.backend.core.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nextlevel.monitoring.backend.config.Configuration;
import com.nextlevel.monitoring.backend.core.common.NliRuntimeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.Collection;


@ConditionalOnProperty(value = "keycloak.enabled", havingValue = "false")
@RestController
@RequestMapping("/api/authentication")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    Configuration configuration;

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping()
    public Token authorize(@RequestBody Credential credential) throws NliRuntimeException {
            Collection<? extends GrantedAuthority > authorities = new ArrayList<>();
            Claims claims = Jwts.claims();

            User principal = new User("admin", "", authorities);
            Authentication auth = new NliUsernamePasswordAuthenticationToken(principal, credential);
            LOGGER.info("Successful login username={}", credential.getUsername());
            Token t = new Token();
            t.setToken(tokenProvider .createToken(auth));
            return t;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Token {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}

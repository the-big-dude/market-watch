package com.nextlevel.monitoring.backend.core.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class RestHelper {

    public static HttpHeaders createHttpHeaders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();
        HttpHeaders headers = new HttpHeaders();
        String basicAuth = "Bearer " + token;
        if(token.length() > 0){
            headers.add("Authorization", basicAuth);
            System.out.println("Authorization is added");
        }
        headers.add("Content-Type", "application/json; charset=utf8");
        return headers;
    }


}

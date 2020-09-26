package com.nextlevel.monitoring.backend.core.client.service;

import com.nextlevel.monitoring.backend.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	Configuration configuration;

	public List<String> searchClients(HttpHeaders headers) {
        try {

            HttpHeaders authHeader = extractAuthHeader(headers);
            HttpEntity<?> httpEntity = new HttpEntity<>(authHeader);

            LOGGER.debug("Before client request url:{}", configuration.getClientUrl());
            ResponseEntity<List<String>> response = restTemplate.exchange(configuration.getClientUrl(), HttpMethod.GET,
                    httpEntity, new ParameterizedTypeReference<List<String>>() {
                    });
            LOGGER.debug("After client request resopnse:{}", response);
            List<String> clients = response.getBody();
            if (clients != null && !clients.isEmpty()) {
            	 java.util.Collections.sort(clients);
            }
            return clients;
        } catch (Exception e) {
            LOGGER.error("Find clients", e);
            throw e;
        }
    }

    private HttpHeaders extractAuthHeader(@RequestHeader HttpHeaders headers) {
        HttpHeaders authHeader = new HttpHeaders();
        if(headers != null && headers.get(HttpHeaders.AUTHORIZATION) != null){
            List<String> authEntries = headers.get(HttpHeaders.AUTHORIZATION);
            if(!authEntries.isEmpty()){
                authHeader.set(HttpHeaders.AUTHORIZATION, authEntries.get(0));
            }
        }
        return authHeader;
    }
}

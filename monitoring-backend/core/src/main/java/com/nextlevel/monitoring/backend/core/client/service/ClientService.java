package com.nextlevel.monitoring.backend.core.client.service;


import org.springframework.http.HttpHeaders;

import java.util.List;

public interface ClientService {

    public List<String> searchClients(HttpHeaders headers);
}

package com.nextlevel.monitoring.backend.core.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.nextlevel.monitoring.backend.core.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


@RestController
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/api/clients", method = RequestMethod.GET)
    @GetMapping(produces = "application/json")
    public List<String> register(@RequestHeader HttpHeaders headers) {

        List<String> clients = clientService.searchClients(headers);
        clients.forEach(client -> LOGGER.debug("client:{}", client));
        return clients;
    }

}

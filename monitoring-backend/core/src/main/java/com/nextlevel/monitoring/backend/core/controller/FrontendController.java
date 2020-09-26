package com.nextlevel.monitoring.backend.core.controller;

import com.nextlevel.monitoring.backend.config.FrontendConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FrontendController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FrontendController.class);

    @Autowired
    private FrontendConfiguration frontendConfiguration;

    @RequestMapping(value = "/frontend/keycloak", method = RequestMethod.GET)
    public KeycloakSetting getKeycloakSettings() {
        LOGGER.debug("get keycloak settings");

        KeycloakSetting keycloakSetting = new KeycloakSetting(frontendConfiguration.getAuthServerUrl(), frontendConfiguration.getResource(), frontendConfiguration.getRealm(),frontendConfiguration.isEnabled());
        LOGGER.trace(keycloakSetting.toString());
        return keycloakSetting;
    }

}

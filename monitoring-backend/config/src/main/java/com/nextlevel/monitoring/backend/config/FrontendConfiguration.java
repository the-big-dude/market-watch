package com.nextlevel.monitoring.backend.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "frontend")
public class FrontendConfiguration {

    @Value("${frontend.keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${frontend.keycloak.resource}")
    private String resource;

    @Value("${frontend.keycloak.realm}")
    private String realm;

    @Value("${keycloak.enabled}")
    private boolean enabled;

    public String getAuthServerUrl() {
        return authServerUrl;
    }

    public void setAuthServerUrl(String authServerUrl) {
        this.authServerUrl = authServerUrl;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

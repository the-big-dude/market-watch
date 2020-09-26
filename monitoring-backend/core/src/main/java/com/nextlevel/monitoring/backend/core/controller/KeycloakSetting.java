package com.nextlevel.monitoring.backend.core.controller;

public class KeycloakSetting {

    private String authServerUrl;

    private String resource;

    private String realm;

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

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public KeycloakSetting(String authServerUrl, String resource, String realm, boolean enabled) {
        this.authServerUrl = authServerUrl;
        this.resource = resource;
        this.realm = realm;
        this.enabled = enabled;
    }
}

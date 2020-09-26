package com.nextlevel.monitoring.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

@Component
@ConfigurationProperties(prefix = "bourse")
@Validated
public class BourceConfiguration {

    private String runJob;

    public String getRunJob() {
        return runJob;
    }

    public void setRunJob(String runJob) {
        this.runJob = runJob;
    }
}
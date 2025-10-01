package io.mosip.id.auth.health.dto;

import java.time.Instant;

public class HealthDetailsResponse {
    private String status;
    private Instant timestamp;
    private String serviceName;
    private String version;
    private String environment;
    private String configuredProperty;

    public HealthDetailsResponse() {}

    public HealthDetailsResponse(String status, Instant timestamp, String serviceName, String version, String environment, String configuredProperty) {
        this.status = status;
        this.timestamp = timestamp;
        this.serviceName = serviceName;
        this.version = version;
        this.environment = environment;
        this.configuredProperty = configuredProperty;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }
    public String getConfiguredProperty() { return configuredProperty; }
    public void setConfiguredProperty(String configuredProperty) { this.configuredProperty = configuredProperty; }
}

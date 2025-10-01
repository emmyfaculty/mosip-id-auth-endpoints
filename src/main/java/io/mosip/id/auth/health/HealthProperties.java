package io.mosip.id.auth.health;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mosip.id.auth")
public class HealthProperties {
    private String serviceName;
    private String version;
    private String environment;
    private String someConfig;
    private boolean forcedDown;

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }
    public String getSomeConfig() { return someConfig; }
    public void setSomeConfig(String someConfig) { this.someConfig = someConfig; }
    public boolean isForcedDown() { return forcedDown; }
    public void setForcedDown(boolean forcedDown) { this.forcedDown = forcedDown; }
}

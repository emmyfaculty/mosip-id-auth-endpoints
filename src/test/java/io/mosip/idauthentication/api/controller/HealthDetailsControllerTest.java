package io.mosip.idauthentication.api.controller;

import io.mosip.id.auth.health.HealthController;
import io.mosip.id.auth.health.HealthProperties;
import io.mosip.id.auth.health.dto.HealthDetailsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HealthDetailsControllerTest {

    @Test
    public void getHealthDetailsReturnsUpStatusWhenServiceIsHealthy() {
        HealthProperties mockProps = mock(HealthProperties.class);
        when(mockProps.isForcedDown()).thenReturn(false);
        when(mockProps.getServiceName()).thenReturn("id-authentication-service");
        when(mockProps.getVersion()).thenReturn("1.0.0");
        when(mockProps.getEnvironment()).thenReturn("production");
        when(mockProps.getSomeConfig()).thenReturn("configValue");

        HealthController controller = new HealthController(mockProps);
        ResponseEntity<HealthDetailsResponse> response = controller.getHealthDetails();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("UP", response.getBody().getStatus());
        assertEquals("id-authentication-service", response.getBody().getServiceName());
        assertEquals("1.0.0", response.getBody().getVersion());
        assertEquals("production", response.getBody().getEnvironment());
        assertEquals("configValue", response.getBody().getConfiguredProperty());
    }

    @Test
    public void getHealthDetailsReturnsDownStatusWhenServiceIsForcedDown() {
        HealthProperties mockProps = mock(HealthProperties.class);
        when(mockProps.isForcedDown()).thenReturn(true);
        when(mockProps.getServiceName()).thenReturn("id-authentication-service");
        when(mockProps.getVersion()).thenReturn("1.0.0");
        when(mockProps.getEnvironment()).thenReturn("production");
        when(mockProps.getSomeConfig()).thenReturn("configValue");

        HealthController controller = new HealthController(mockProps);
        ResponseEntity<HealthDetailsResponse> response = controller.getHealthDetails();

        assertEquals(503, response.getStatusCodeValue());
        assertEquals("DOWN", response.getBody().getStatus());
        assertEquals("id-authentication-service", response.getBody().getServiceName());
        assertEquals("1.0.0", response.getBody().getVersion());
        assertEquals("production", response.getBody().getEnvironment());
        assertEquals("configValue", response.getBody().getConfiguredProperty());
    }
}

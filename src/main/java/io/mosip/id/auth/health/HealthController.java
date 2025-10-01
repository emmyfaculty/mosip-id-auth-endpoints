package io.mosip.id.auth.health;

import io.mosip.id.auth.health.dto.HealthDetailsResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/health")
@Tag(name = "Health API", description = "Service health and configuration details")
public class HealthController {

    private final HealthProperties props;

    public HealthController(HealthProperties props) {
        this.props = props;
    }

    @Operation(
            summary = "Get health details",
            description = "Returns service health, metadata, and a configurable property",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Service is UP"),
                    @ApiResponse(responseCode = "503", description = "Service is DOWN")
            }
    )
    @GetMapping("/details")
    public ResponseEntity<HealthDetailsResponse> getHealthDetails() {
        String status = props.isForcedDown() ? "DOWN" : "UP";
        HealthDetailsResponse resp = new HealthDetailsResponse(
                status,
                Instant.now(),
                props.getServiceName(),
                props.getVersion(),
                props.getEnvironment(),
                props.getSomeConfig()
        );

        if ("DOWN".equals(status)) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(resp);
        }
        return ResponseEntity.ok(resp);
    }
}

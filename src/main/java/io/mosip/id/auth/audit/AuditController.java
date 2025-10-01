package io.mosip.id.auth.audit;

import io.mosip.id.auth.audit.dto.AuditRequest;
import io.mosip.id.auth.audit.dto.AuditResponse;
import io.mosip.id.auth.audit.entity.AuditEvent;
import io.mosip.id.auth.audit.service.AuditService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/audit")
@Tag(name = "Audit API", description = "API for logging and retrieving audit events")
public class AuditController {

    private final AuditService service;

    public AuditController(AuditService service) {
        this.service = service;
    }

    @Operation(
            summary = "Log an audit/event",
            description = "Accepts an event payload and stores it in the audit log",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Audit event logged successfully"),
                    @ApiResponse(responseCode = "400", description = "Validation error"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/log")
    public ResponseEntity<AuditResponse> logEvent(@Valid @RequestBody AuditRequest request) {
        AuditEvent saved = service.createEvent(request);
        AuditResponse resp = new AuditResponse(saved.getId(), saved.getTimestamp());
        return ResponseEntity.ok(resp);
    }
}

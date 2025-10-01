package io.mosip.idauthentication.api.controller;


import io.mosip.id.auth.audit.AuditController;
import io.mosip.id.auth.audit.dto.AuditRequest;
import io.mosip.id.auth.audit.dto.AuditResponse;
import io.mosip.id.auth.audit.entity.AuditEvent;
import io.mosip.id.auth.audit.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuditLogControllerTest {

    private AuditService auditLogService;
    private AuditController auditLogController;

    @BeforeEach
    public void setup() {
        auditLogService = mock(AuditService.class);
        auditLogController = new AuditController(auditLogService);
    }

    @Test
    public void createAuditLogReturnsSuccessResponse() {
        AuditRequest request = new AuditRequest();
        request.setEventType("LOGIN");
        request.setDescription("User logged in");
        request.setUserId("user123");

        AuditEvent mockEvent = new AuditEvent("LOGIN", "User logged in", "event123",  Instant.parse("2025-10-01T10:00:00Z"));
        mockEvent.setId(123L);
        when(auditLogService.createEvent(request)).thenReturn(mockEvent);

        ResponseEntity<AuditResponse> response = auditLogController.logEvent(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(123, response.getBody().getEventId());
        assertEquals(Instant.parse("2025-10-01T10:00:00Z"), response.getBody().getTimestamp());
    }

    @Test
    public void createAuditLogThrowsValidationExceptionForInvalidRequest() {
        AuditRequest request = new AuditRequest();
        request.setEventType(null);

        Exception exception = assertThrows(Exception.class, () -> {
            auditLogController.logEvent(request);
        });

    }

    @Test
    public void createAuditLogHandlesServiceFailureGracefully() {
        AuditRequest request = new AuditRequest();
        request.setEventType("LOGIN");
        request.setDescription("User logged in");
        request.setUserId("user123");

        when(auditLogService.createEvent(request)).thenThrow(new RuntimeException("Service failure"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            auditLogController.logEvent(request);
        });

        assertEquals("Service failure", exception.getMessage());
    }
}

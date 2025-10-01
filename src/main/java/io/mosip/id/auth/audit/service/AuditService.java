package io.mosip.id.auth.audit.service;

import io.mosip.id.auth.audit.dto.AuditRequest;
import io.mosip.id.auth.audit.entity.AuditEvent;
import io.mosip.id.auth.audit.repository.AuditEventRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuditService {

    private final AuditEventRepository repository;

    public AuditService(AuditEventRepository repository) {
        this.repository = repository;
    }

    public AuditEvent createEvent(AuditRequest req) {
        AuditEvent e = new AuditEvent(
                req.getEventType(),
                req.getDescription(),
                req.getUserId(),
                Instant.now()
        );
        return repository.save(e);
    }
}

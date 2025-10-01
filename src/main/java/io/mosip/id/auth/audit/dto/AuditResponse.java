package io.mosip.id.auth.audit.dto;

import java.time.Instant;

public class AuditResponse {
    private Long eventId;
    private Instant timestamp;

    public AuditResponse(String event123, String s) {}
    public AuditResponse(Long eventId, Instant timestamp) {
        this.eventId = eventId;
        this.timestamp = timestamp;
    }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}

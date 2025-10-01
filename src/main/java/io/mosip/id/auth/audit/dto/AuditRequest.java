package io.mosip.id.auth.audit.dto;

import jakarta.validation.constraints.NotBlank;

public class AuditRequest {

    @NotBlank(message = "eventType is mandatory")
    private String eventType;

    private String description;

    @NotBlank(message = "userId is mandatory")
    private String userId;

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}

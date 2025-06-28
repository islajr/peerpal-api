package org.project.peerpalapi.dto.reset.responses;

public record ResetResponse(
        String message
) {
    public ResetResponse(String message) {
        this.message = message;
    }
}

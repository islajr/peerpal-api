package org.project.peerpalapi.dto.reset.responses;

public record ResetResponseDTO(
        String message
) {
    public ResetResponseDTO(String message) {
        this.message = message;
    }
}

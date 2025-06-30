package org.project.peerpalapi.dto.auth.requests;

public record VerificationDTO(
        String email,
        int code
) {
}

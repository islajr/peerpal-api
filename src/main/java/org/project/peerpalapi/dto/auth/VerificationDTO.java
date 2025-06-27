package org.project.peerpalapi.dto.auth;

public record VerificationDTO(
    String email,
    int code
) {
}

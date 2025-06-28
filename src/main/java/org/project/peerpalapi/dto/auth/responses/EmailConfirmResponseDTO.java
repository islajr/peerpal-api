package org.project.peerpalapi.dto.auth.responses;

public record EmailConfirmResponseDTO(
        String message,
        String email
) {
}

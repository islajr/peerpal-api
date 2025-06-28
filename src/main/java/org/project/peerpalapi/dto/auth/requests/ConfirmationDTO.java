package org.project.peerpalapi.dto.auth.requests;

public record ConfirmationDTO(
    String email,
    int code
) {
}

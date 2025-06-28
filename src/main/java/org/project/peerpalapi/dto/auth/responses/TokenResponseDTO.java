package org.project.peerpalapi.dto.auth.responses;

public record TokenResponseDTO(
        String accessToken,
        String refreshToken
) {
}

package org.project.peerpalapi.dto.websocket.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DeleteChatMessageRequest(
        @NotNull
        @NotBlank
        String message,

        @NotNull
        @NotBlank
        LocalDateTime timestamp
) {
}

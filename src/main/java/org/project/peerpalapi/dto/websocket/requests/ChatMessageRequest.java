package org.project.peerpalapi.dto.websocket.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ChatMessageRequest(

        @NotNull
        @NotBlank
        String recipient,   //  username of the recipient

        @NotNull
        @NotBlank
        String message,  // message

        @NotNull
        @NotBlank
        LocalDateTime timestamp
) {
}

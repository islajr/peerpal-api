package org.project.peerpalapi.dto.websocket.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record NewChatRequest(

    @NotNull
    @NotBlank
    String recipient,    // username of the recipient,

    @NotNull
    @NotBlank
    String message,  // intended chat message

    @NotNull
    @NotBlank
    LocalDateTime timestamp
) {
}

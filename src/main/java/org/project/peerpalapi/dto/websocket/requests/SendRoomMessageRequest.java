package org.project.peerpalapi.dto.websocket.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SendRoomMessageRequest(

        @NotNull
        @NotBlank
        String sender,  // name of the sender

        @NotNull
        @NotBlank
        String message, // message content

        @NotNull
        @NotBlank
        LocalDateTime timestamp    // timestamp
) {
}

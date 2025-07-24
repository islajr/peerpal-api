package org.project.peerpalapi.dto.websocket.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DeleteRoomMessageRequest(

        @NotNull
        @NotBlank
        String room,    // room name

        @NotBlank
        @NotNull
        String message,

        @NotBlank
        @NotNull
        LocalDateTime timestamp,    // message timestamp

        @NotBlank
        @NotNull
        String sender   //  name of the sender
) {
}

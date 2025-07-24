package org.project.peerpalapi.dto.websocket.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JoinRoomRequest(

        @NotNull
        @NotBlank
        String room    // room name
) {
}

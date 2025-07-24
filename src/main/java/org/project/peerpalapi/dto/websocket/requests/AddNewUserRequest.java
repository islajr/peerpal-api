package org.project.peerpalapi.dto.websocket.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddNewUserRequest(
        @NotNull
        @NotBlank
        String user,    // intended user

        @NotNull
        @NotBlank
        String room    // intended room name
) {
}

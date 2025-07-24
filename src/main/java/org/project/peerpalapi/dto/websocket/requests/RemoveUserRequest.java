package org.project.peerpalapi.dto.websocket.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RemoveUserRequest(

        @NotNull
        @NotBlank
        String user,    // name of the user to remove

        @NotNull
        @NotBlank
        String room     // name of the room to remove user from
) {
}

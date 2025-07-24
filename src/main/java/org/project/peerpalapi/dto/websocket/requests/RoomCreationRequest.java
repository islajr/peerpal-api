package org.project.peerpalapi.dto.websocket.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RoomCreationRequest(

        @NotNull
        String name,
        String description, // if any

        @NotNull
        @Size(min = 2)  // a room has to have at least two people
        List<String> members
) {
}

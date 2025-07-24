package org.project.peerpalapi.dto.websocket.responses;

import lombok.Builder;

@Builder
public record ActionResponse(
        int header, // mimics http headers
        String message
) {
}

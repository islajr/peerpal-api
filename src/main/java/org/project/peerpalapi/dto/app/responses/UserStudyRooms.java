package org.project.peerpalapi.dto.app.responses;

import lombok.Builder;
import org.project.peerpalapi.entity.Room;

import java.util.ArrayList;

@Builder
public record UserStudyRooms(
        ArrayList<Room> userRooms
) {
}

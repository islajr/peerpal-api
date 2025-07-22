package org.project.peerpalapi.dto.app.responses;

import lombok.Builder;
import org.project.peerpalapi.entity.Room;

import java.util.ArrayList;

@Builder
public record UserDashboard(
        int roomsJoined,
        int tasksCompleted,
        int quizzesPassed,
        ArrayList<Room> featuredRooms
) {
}

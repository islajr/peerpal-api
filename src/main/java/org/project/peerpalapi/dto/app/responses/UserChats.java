package org.project.peerpalapi.dto.app.responses;

import lombok.Builder;
import org.project.peerpalapi.entity.Chat;
import org.project.peerpalapi.entity.Room;

import java.util.ArrayList;

@Builder
public record UserChats(
        ArrayList<Room> rooms,
        ArrayList<Chat> chats
) {
}

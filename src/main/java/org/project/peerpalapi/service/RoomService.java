package org.project.peerpalapi.service;

import lombok.RequiredArgsConstructor;
import org.project.peerpalapi.dto.websocket.responses.ActionResponse;
import org.project.peerpalapi.dto.websocket.requests.*;
import org.project.peerpalapi.repository.MessageRepository;
import org.project.peerpalapi.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;

    public ActionResponse createRoom(String accessId, RoomCreationRequest roomCreationRequest) {
    }

    public ActionResponse joinRoom(String accessId, JoinRoomRequest joinRoomRequest) {
    }

    public ActionResponse addUserToRoom(String accessId, AddNewUserRequest addNewUserRequest) {
    }

    public ActionResponse exitRoom() {
    }

    public ActionResponse removeUserFromRoom(String accessId, RemoveUserRequest removeUserRequest) {
    }

    public ActionResponse deleteRoomMessage(String accessId, DeleteRoomMessageRequest deleteRoomMessageRequest) {
    }

    public ActionResponse sendRoomMessage(String accessId, SendRoomMessageRequest sendRoomMessageRequest) {
    }
}

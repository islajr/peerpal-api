package org.project.peerpalapi.controller;

import lombok.RequiredArgsConstructor;
import org.project.peerpalapi.dto.websocket.responses.ActionResponse;
import org.project.peerpalapi.dto.websocket.requests.*;
import org.project.peerpalapi.service.RoomService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebsocketRoomController {

    private final RoomService roomService;

    // create a new room
    @MessageMapping("/room/new")
    @SendTo("/room/{accessId}")
    public ActionResponse createRoom(@DestinationVariable String accessId, @Payload RoomCreationRequest roomCreationRequest) {
        return roomService.createRoom(accessId, roomCreationRequest);
    }

    // join an existing room
    @MessageMapping("/room/join")
    @SendTo("/room/{accessId}")
    public ActionResponse joinRoom(@DestinationVariable String accessId, @Payload JoinRoomRequest joinRoomRequest) {
        return roomService.joinRoom(accessId, joinRoomRequest);
    }

    // add a user to an existing room
    @MessageMapping("/room/user.add")
    @SendTo("/room/{accessId}")
    public ActionResponse addUserToRoom(@DestinationVariable String accessId, @Payload AddNewUserRequest addNewUserRequest) {
        return roomService.addUserToRoom(accessId, addNewUserRequest);
    }

    // send a message to a room
    @MessageMapping("/room/message.send")
    @SendTo("/room/{accessId}")
    public ActionResponse sendRoomMessage(@DestinationVariable String accessId, @Payload SendRoomMessageRequest sendRoomMessageRequest) {
        return roomService.sendRoomMessage(accessId, sendRoomMessageRequest);
    }

    // delete a message from a room
    @MessageMapping("/room/message.delete")
    @SendTo("/room/{accessId}")
    public ActionResponse deleteRoomMessage(@DestinationVariable String accessId, @Payload DeleteRoomMessageRequest deleteRoomMessageRequest) {
        return roomService.deleteRoomMessage(accessId, deleteRoomMessageRequest);
    }

    // remove a user from a room
    @MessageMapping("/room/user.remove")
    @SendTo("/room/{accessId}")
    public ActionResponse removeUserFromRoom(@DestinationVariable String accessId, @Payload RemoveUserRequest removeUserRequest) {
        return roomService.removeUserFromRoom(accessId, removeUserRequest);
    }

    // exit a room
    @MessageMapping("/room/exit")
    @SendTo("/room/{accessId}")
    public ActionResponse exitRoom(@DestinationVariable String accessId) {
        return roomService.exitRoom(accessId);
    }
}

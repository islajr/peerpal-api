package org.project.peerpalapi.service;

import lombok.RequiredArgsConstructor;
import org.project.peerpalapi.dto.websocket.responses.ActionResponse;
import org.project.peerpalapi.dto.websocket.requests.*;
import org.project.peerpalapi.entity.Message;
import org.project.peerpalapi.entity.Room;
import org.project.peerpalapi.entity.User;
import org.project.peerpalapi.exceptions.auth.AuthException;
import org.project.peerpalapi.repository.AuthRepository;
import org.project.peerpalapi.repository.MessageRepository;
import org.project.peerpalapi.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;
    private final AuthRepository authRepository;

    public ActionResponse createRoom(String accessId, RoomCreationRequest roomCreationRequest) {

        Room room = Room.builder()
                .accessId(accessId)
                .name(roomCreationRequest.name())
                .description(roomCreationRequest.description())
                .members(null)  // match names to user entities in a helper function and return values in a list
                .admins(null)   // same as above
                .createdBy(null)    // obtain user from authentication
                .messages(null)
                .createdAt(LocalDateTime.now())
                .updatedAt(null).build();

        roomRepository.save(room);
        return ActionResponse.builder()
                .header(201)
                .message("successfully created new room").build();

    }

    public ActionResponse joinRoom(String accessId, JoinRoomRequest joinRoomRequest) {
        Room room = roomRepository.findRoomByNameAndAccessId(joinRoomRequest.room(), accessId);

        if (room != null) {
            // prompt group admins to accept join request
            // if they agree, add user to the list of members and continue
            // if not, reject the action
            return ActionResponse.builder()
                    .header(200)
                    .message("successfully joined room")
                    .build();
        }

        throw new AuthException(404, "Room not found");
    }

    public ActionResponse addUserToRoom(String accessId, AddNewUserRequest addNewUserRequest) {

        // check if user has the permission to add other users to the room
        // if so, continue...
        // otherwise, reject the action
        Room room = roomRepository.findRoomByNameAndAccessId(addNewUserRequest.room(), accessId);

        if (room != null) {
            // check the permissions and proceed as detailed above
            return ActionResponse.builder()
                    .header(200)
                    .message("added user to room")
                    .build();

        }

        throw new AuthException(404, "Room not found");

    }

    public ActionResponse exitRoom(String accessId) {

        // grab user details from security context
        Room room = roomRepository.findRoomByAccessId(accessId);

        room.getMembers().forEach(member -> {
            // check for the user and delete them from the room
        });

        return ActionResponse.builder()
                .header(200)
                .message("successfully exited room")
                .build();

    }

    public ActionResponse removeUserFromRoom(String accessId, RemoveUserRequest removeUserRequest) {
        // check if user has the permission to remove other users from the room
        // if so, continue...
        // otherwise, reject the action
        Room room = roomRepository.findRoomByNameAndAccessId(removeUserRequest.room(), accessId);

        if (room != null) {
            // check the permissions and proceed as detailed above
            room.getMembers().forEach(member -> {
                // peruse for the user provided in the "RemoveUserRequest" and remove them
            });
            return ActionResponse.builder()
                    .header(200)
                    .message("successfully removed user from room")
                    .build();

        }

        throw new AuthException(404, "Room not found");
    }

    public ActionResponse deleteRoomMessage(String accessId, DeleteRoomMessageRequest deleteRoomMessageRequest) {
        Room room = roomRepository.findRoomByNameAndAccessId(deleteRoomMessageRequest.room(), accessId);
        User sender = authRepository.findUserByFullName(deleteRoomMessageRequest.sender());

        if (room != null) {
            Message message = messageRepository.findMessageByContentAndSenderAndTimestamp(
                    deleteRoomMessageRequest.message(), sender, deleteRoomMessageRequest.timestamp());

            if (message.getRoom() != null && message.getRoom().equals(room)) {    // if it's a confirmed room message
                // check and confirm that the sender is the same as the deleter
                // proceed to delete message
                messageRepository.delete(message);
            }

            return ActionResponse.builder()
                    .header(200)
                    .message("successfully removed user from room")
                    .build();

        }

        throw new AuthException(404, "Room not found");
    }

    public ActionResponse sendRoomMessage(String accessId, SendRoomMessageRequest sendRoomMessageRequest) {
        Room room = roomRepository.findRoomByAccessId(accessId);
        User sender = authRepository.findUserByFullName(sendRoomMessageRequest.sender());

        if (room != null && room.getName().equals(sendRoomMessageRequest.room())) {
            // build and send the message
            Message message = Message.builder()
                    .sender(sender)
                    .content(sendRoomMessageRequest.message())
                    .ancestor(null) // assumption for now
                    .isReply(false)  // assumption for now
                    .room(room)
                    .recipient(null)
                    .chat(null) // is not a chat message
                    .timestamp(LocalDateTime.now())
                    .build();

            room.getMessages().add(message);
            messageRepository.save(message);
            roomRepository.save(room);

            return ActionResponse.builder()
                    .header(200)
                    .message("successfully sent message to room")
                    .build();
        }
        throw new AuthException(404, "Room not found");
    }
}

package org.project.peerpalapi.service;

import lombok.RequiredArgsConstructor;
import org.project.peerpalapi.dto.websocket.requests.ChatMessageRequest;
import org.project.peerpalapi.dto.websocket.requests.DeleteChatMessageRequest;
import org.project.peerpalapi.dto.websocket.requests.NewChatRequest;
import org.project.peerpalapi.dto.websocket.responses.ActionResponse;
import org.project.peerpalapi.entity.Chat;
import org.project.peerpalapi.entity.Message;
import org.project.peerpalapi.entity.User;
import org.project.peerpalapi.exceptions.auth.AuthException;
import org.project.peerpalapi.repository.AuthRepository;
import org.project.peerpalapi.repository.ChatRepository;
import org.project.peerpalapi.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalChatService {

    private final ChatRepository chatRepository;
    private final AuthRepository authRepository;
    private final MessageRepository messageRepository;

    public ActionResponse createNewChat(String accessId, NewChatRequest newChatRequest) {

        User recipient = authRepository.findUserByFullName(newChatRequest.recipient());

        Chat chat = Chat.builder()
                .members(null)  // add the user entities of both sender and recipient
                .accessId(accessId)
                .messages(new ArrayList<>()) // initially empty
                .createdAt(LocalDateTime.now())
                .updatedAt(null)    // initially
                .build();

        Message message = Message.builder()
                .content(newChatRequest.message())
                .recipient(recipient)
                .sender(null)       // grab sender from auth
                .chat(chat)
                .room(null)
                .isReply(false)
                .ancestor(null)
                .timestamp(newChatRequest.timestamp())
                .build();

        // add message to chat
        chat.addMessage(message);

        // save message
        messageRepository.save(message);

        // save chat
        chatRepository.save(chat);

        return ActionResponse.builder()
                .header(201)
                .message("successfully created new room").build();

    }

    public ActionResponse sendPersonalChatMessage(String accessId, ChatMessageRequest chatMessageRequest) {
        User recipient = authRepository.findUserByFullName(chatMessageRequest.recipient());
        Chat chat = chatRepository.findChatByAccessId(accessId);

        if (chat != null) {
            Message message = Message.builder()
                    .content(chatMessageRequest.message())
                    .recipient(recipient)
                    .sender(null)       // grab sender from auth
                    .chat(chat)
                    .room(null)
                    .isReply(false)
                    .ancestor(null)
                    .timestamp(chatMessageRequest.timestamp())
                    .build();

            // add message to chat
            chat.addMessage(message);

            // save message
            messageRepository.save(message);

            // save chat
            chatRepository.save(chat);

            return ActionResponse.builder()
                    .header(201)
                    .message("successfully created new room").build();
        }
        throw new AuthException(404, "Chat not found");


    }

    public ActionResponse deletePersonalChatMessage(String accessId, DeleteChatMessageRequest deleteChatMessageRequest) {
        Chat chat = chatRepository.findChatByAccessId(accessId);
        if (chat != null) {
            List<Message> messages = messageRepository.findMessageByContentAndTimestamp(deleteChatMessageRequest.message(), deleteChatMessageRequest.timestamp());
            for (Message _message : messages) {
                if (_message.getChat().equals(chat)) {
                    messageRepository.delete(_message);
                    chat.getMessages().remove(_message);
                    chatRepository.save(chat);

                    return ActionResponse.builder()
                            .header(201)
                            .message("successfully created new room").build();

                }
            }

            throw new AuthException(404, "No such message");
        }

        throw new AuthException(404, "Chat not found");

    }
}

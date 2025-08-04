package org.project.peerpalapi.controller;

import lombok.RequiredArgsConstructor;
import org.project.peerpalapi.dto.websocket.requests.ChatMessageRequest;
import org.project.peerpalapi.dto.websocket.requests.DeleteChatMessageRequest;
import org.project.peerpalapi.dto.websocket.requests.NewChatRequest;
import org.project.peerpalapi.dto.websocket.responses.ActionResponse;
import org.project.peerpalapi.service.PersonalChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PersonalChatController {

    private final PersonalChatService personalChatService;

    // create a new chat
    @MessageMapping("/chat/new")
    @SendTo("/chat/{accessId}")
    public ActionResponse createNewChat(@DestinationVariable String accessId, NewChatRequest newChatRequest, Principal user) {
        return personalChatService.createNewChat(accessId, newChatRequest, user);
    }

    // send a new (personal) chat message
    @MessageMapping("/chat/send")
    @SendTo("/chat/{accessId}")
    public ActionResponse sendPersonalChatMessage(@DestinationVariable String accessId, ChatMessageRequest chatMessageRequest, Principal user) {
        return personalChatService.sendPersonalChatMessage(accessId, chatMessageRequest, user);
    }

    // delete a personal chat message
    @MessageMapping("/chat/delete")
    @SendTo("/chat/{accessId}")
    public ActionResponse deletePersonalChatMessage(@DestinationVariable String accessId, DeleteChatMessageRequest deleteChatMessageRequest, Principal user) {
        return personalChatService.deletePersonalChatMessage(accessId, deleteChatMessageRequest, user);
    }
}

package org.project.peerpalapi.controller;

import lombok.RequiredArgsConstructor;
import org.project.peerpalapi.dto.websocket.requests.ChatMessageRequest;
import org.project.peerpalapi.dto.websocket.requests.NewChatRequest;
import org.project.peerpalapi.dto.websocket.responses.ActionResponse;
import org.project.peerpalapi.service.PersonalChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PersonalChatController {

    private final PersonalChatService personalChatService;

    // create a new chat
    @MessageMapping("/chat/new")
    @SendTo("/chat/{accessId}")
    public ActionResponse createNewChat(@DestinationVariable String accessId, NewChatRequest newChatRequest) {
        return personalChatService.createNewChat(accessId, newChatRequest);
    }

    // send a new (personal) chat message
    @MessageMapping("/chat/send")
    @SendTo("/chat/{accessId}")
    public ActionResponse sendPersonalChatMessage(@DestinationVariable String accessId, ChatMessageRequest chatMessageRequest) {
        return personalChatService.sendPersonalChatMessage(accessId, chatMessageRequest);
    }

    // delete a personal chat message
    @MessageMapping("/chat/delete")
    @SendTo("/chat/{accessId}")
    public ActionResponse deletePersonalChatMessage(@DestinationVariable String accessId, ChatMessageRequest chatMessageRequest) {
        return personalChatService.deletePersonalChatMessage(accessId, chatMessageRequest);
    }
}

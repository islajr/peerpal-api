package org.project.peerpalapi.service;

import lombok.RequiredArgsConstructor;
import org.project.peerpalapi.dto.websocket.requests.ChatMessageRequest;
import org.project.peerpalapi.dto.websocket.requests.NewChatRequest;
import org.project.peerpalapi.dto.websocket.responses.ActionResponse;
import org.project.peerpalapi.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalChatService {

    private final ChatRepository chatRepository;

    public ActionResponse createNewChat(String accessId, NewChatRequest newChatRequest) {

    }

    public ActionResponse sendPersonalChatMessage(String accessId, ChatMessageRequest chatMessageRequest) {
    }

    public ActionResponse deletePersonalChatMessage(String accessId, ChatMessageRequest chatMessageRequest) {
    }
}

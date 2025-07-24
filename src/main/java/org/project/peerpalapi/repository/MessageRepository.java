package org.project.peerpalapi.repository;

import org.project.peerpalapi.entity.Message;
import org.project.peerpalapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

    Message findMessageByContentAndSenderAndTimestamp(String content, User sender, LocalDateTime timestamp);
}

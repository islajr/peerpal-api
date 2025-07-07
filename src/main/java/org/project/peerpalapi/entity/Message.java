package org.project.peerpalapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    String content;

    @OneToOne
    User sender;

    @OneToOne
    User receiver;

    boolean isReply;

    @OneToOne
    Message ancestor;   // initial 'reply' logic

    LocalDateTime timestamp;
}

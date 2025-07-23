package org.project.peerpalapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Column(name = "content")
    @Size(min = 1)
    String content;

    @OneToOne
    @JoinColumn(name = "sender", nullable = false)
    User sender;

    @OneToOne
    User recipient;

    @Column(name = "is_reply", nullable = false)
    boolean isReply;

    @OneToOne
    @JoinColumn(name = "ancestor")
    Message ancestor;   // initial 'reply' logic

    LocalDateTime timestamp;
}

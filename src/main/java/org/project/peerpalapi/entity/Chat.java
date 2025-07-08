package org.project.peerpalapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany
    @JoinColumn(name = "members")
    ArrayList<User> members;

    @OneToMany()
    @JoinColumn(name = "messages")
    ArrayList<Message> messages;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

}

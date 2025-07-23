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
    ArrayList<User> members;

    @OneToMany
    ArrayList<Message> messages;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

}

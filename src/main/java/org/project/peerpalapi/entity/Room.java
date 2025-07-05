package org.project.peerpalapi.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;

    @OneToMany
    ArrayList<User> members;

    @OneToMany
    ArrayList<User> admins;

    String description; // short description of the group

    String bio;

    Long photoId;   // group photo storage

    @OneToMany
    ArrayList<Message> messages;

    @OneToOne
    User createdBy;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

package org.project.peerpalapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @Column(name = "name")
    @Size(min = 1)
    String name;

    @OneToMany
    @JoinColumn(name = "members")
    ArrayList<User> members;

    @OneToMany
    @JoinColumn(name = "admins")
    ArrayList<User> admins;

    @Column(name = "description")
    @Size(min = 1)
    String description; // short description of the group


//    String bio;

    @Column(name = "photo_id")
    Long photoId;   // group photo storage

    @OneToMany
    @JoinColumn(name = "messages")
    ArrayList<Message> messages;

    @OneToOne
    @JoinColumn(name = "created_by", nullable = false)
    User createdBy;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

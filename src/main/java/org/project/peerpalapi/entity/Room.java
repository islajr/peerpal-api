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
import java.util.List;

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
    @Size(min = 1)
    String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    List<User> members;

    @OneToMany
    ArrayList<User> admins;

    @Size(min = 1)
    String description; // short description of the group


//    String bio;

    /*@Column(name = "photo_id")
    Long photoId;   // group photo storage*/

    @OneToMany
    ArrayList<Message> messages;

    @OneToOne
    @JoinColumn(name = "created_by", nullable = false)
    User createdBy;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}

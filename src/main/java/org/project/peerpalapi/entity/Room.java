package org.project.peerpalapi.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "rooms")
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, name = "access_id", nullable = false)
    private String accessId;    // provides websocket access from the frontend.

    @NotNull
    @Size(min = 1)
    private String name;

    @ManyToMany(mappedBy = "userRooms")
    private List<User> members;

    @ManyToMany
    @JoinTable(
        name = "room_admins",
        joinColumns = @JoinColumn(name = "room_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> admins;

    @Size(min = 1)
    private String description; // short description of the group


//    String bio;

    /*@Column(name = "photo_id")
    Long photoId;   // group photo storage*/

    @OneToMany(mappedBy = "room")
    private List<Message> messages;

    @OneToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void addMessage(Message message) {
        messages.add(message);
    }
}

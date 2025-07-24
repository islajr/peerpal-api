package org.project.peerpalapi.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "full_name")
    @Size(min = 1)
    private String fullName;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "email")
    @Size(min = 5, max = 100)
    private String email;

    @NotNull
    @Column(name = "password")
    @Size(min = 8, max = 75)
    private String password;

    @NotNull
    @Column(name = "is_email_verified")
    private boolean isEmailVerified;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_rooms",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Room> userRooms;

    /* @OneToMany
    @JoinColumn(name = "tasks")
    private List<Task> userTasks; */

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String generateFirstName() {
        return fullName.split(" ")[0];
    }
}

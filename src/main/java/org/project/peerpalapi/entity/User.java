package org.project.peerpalapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "full_name")
    @Size(min = 1)
    String fullName;

    @NotNull
    @Column(name = "first_name")
    String firstName;

    @NotNull
    @Column(name = "email")
    @Size(min = 5, max = 100)
    String email;

    @NotNull
    @Column(name = "password")
    @Size(min = 8, max = 75)
    private String password;

    @NotNull
    @Column(name = "is_email_verified")
    boolean isEmailVerified;

    /*@Column(name = "rooms")
    @OneToMany
    ArrayList<Room> rooms;*/

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public String generateFirstName() {
        return fullName.split(" ")[0];
    }
}

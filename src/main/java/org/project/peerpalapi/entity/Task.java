package org.project.peerpalapi.entity;

import jakarta.persistence.*;
import org.project.peerpalapi.entity.enums.Status;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    String task;

    Status status_id;

    String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}

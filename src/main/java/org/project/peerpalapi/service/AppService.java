package org.project.peerpalapi.service;

import lombok.RequiredArgsConstructor;
import org.project.peerpalapi.dto.app.responses.*;
import org.project.peerpalapi.entity.User;
import org.project.peerpalapi.repository.AuthRepository;
import org.springframework.stereotype.Service;

import static org.project.peerpalapi.util.Utilities.obtainUser;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AuthRepository authRepository;

    public UserDashboard getUserDashboard() {

        User user = obtainUser();

        return UserDashboard.builder()
                // populate later
                .build();

    }

    public UserStudyRooms getUserStudyRooms() {

        User user = obtainUser();

        return UserStudyRooms.builder()
                // populate later
                .build();
    }

    public UserChats getUserChats() {

        User user = obtainUser();

        return UserChats.builder()
                // populate later
                .build();
    }

    public UserCalendar getUserCalendar() {

        User user = obtainUser();

        return UserCalendar.builder()
                // populate later
                .build();
    }

    public UserTasks getUserTasks() {

        User user = obtainUser();

        return UserTasks.builder()
                // populate later
                .build();

    }

    public UserGoalsAndStreaks getUserGoalsAndStreaks() {

        User user = obtainUser();

        return UserGoalsAndStreaks.builder()
                // populate later
                .build();
    }

    public UserSettings getUserSettings() {

        User user = obtainUser();

        return UserSettings.builder()
                // populate later
                .build();
    }
}

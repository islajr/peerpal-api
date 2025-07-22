package org.project.peerpalapi.service;

import lombok.RequiredArgsConstructor;
import org.project.peerpalapi.dto.app.responses.*;
import org.project.peerpalapi.entity.User;
import org.project.peerpalapi.entity.UserPrincipal;
import org.project.peerpalapi.repository.AuthRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AuthRepository authRepository;

    public UserDashboard getUserDashboard() {

        String email = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getDetails()).getEmail();
        User user = authRepository.findUserByEmail(email);

        return UserDashboard.builder()
                // populate later
                .build();

    }

    public UserStudyRooms getUserStudyRooms() {

        String email = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getDetails()).getEmail();
        User user = authRepository.findUserByEmail(email);

        return UserStudyRooms.builder()
                // populate later
                .build();
    }

    public UserChats getUserChats() {

        String email = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getDetails()).getEmail();
        User user = authRepository.findUserByEmail(email);

        return UserChats.builder()
                // populate later
                .build();
    }

    public UserCalendar getUserCalendar() {

        String email = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getDetails()).getEmail();
        User user = authRepository.findUserByEmail(email);

        return UserCalendar.builder()
                // populate later
                .build();
    }

    public UserTasks getUserTasks() {

        String email = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getDetails()).getEmail();
        User user = authRepository.findUserByEmail(email);

        return UserTasks.builder()
                // populate later
                .build();

    }

    public UserGoalsAndStreaks getUserGoalsAndStreaks() {

        String email = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getDetails()).getEmail();
        User user = authRepository.findUserByEmail(email);

        return UserGoalsAndStreaks.builder()
                // populate later
                .build();
    }

    public UserSettings getUserSettings() {

        String email = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getDetails()).getEmail();
        User user = authRepository.findUserByEmail(email);

        return UserSettings.builder()
                // populate later
                .build();
    }
}

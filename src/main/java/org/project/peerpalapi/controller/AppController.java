package org.project.peerpalapi.controller;

import org.project.peerpalapi.dto.app.responses.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/peerpal/app")
public class AppController {

    @GetMapping("/dashboard")
    public UserDashboard getDashboard() {
        return null;
    }

    @GetMapping("/rooms")
    public UserStudyRooms getStudyRooms() {
        return null;
    }

    @GetMapping("/chats")
    public UserChats getChats() {
        return null;
    }

    @GetMapping("/calendar")
    public UserCalendar getCalendar() {
        return null;
    }

    @GetMapping("/tasks")
    public UserTasks getTasks() {
        return null;
    }

    @GetMapping("/goalStreaks")
    public UserGoalsAndStreaks getGoalsAndStreaks() {
        return null;
    }

    @GetMapping("/settings")
    public UserSettings getSettings() {
        return null;
    }
}

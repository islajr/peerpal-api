package org.project.peerpalapi.controller;

import lombok.RequiredArgsConstructor;
import org.project.peerpalapi.dto.app.responses.*;
import org.project.peerpalapi.service.AppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/peerpal/app")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    /*
    * This controller serves the main application sections, namely:
    * - dashboard
    * - rooms
    * - chats
    * - calendar
    * - tasks
    * - goals and streaks
    * - settings
    * */

    @GetMapping("/dashboard")
    public UserDashboard getDashboard() {
        return appService.getUserDashboard();
    }

    @GetMapping("/rooms")
    public UserStudyRooms getStudyRooms() {
        return appService.getUserStudyRooms();
    }

    @GetMapping("/chats")
    public UserChats getChats() {
        return appService.getUserChats();
    }

    @GetMapping("/calendar")
    public UserCalendar getCalendar() {
        return appService.getUserCalendar();
    }

    @GetMapping("/tasks")
    public UserTasks getTasks() {
        return appService.getUserTasks();
    }

    @GetMapping("/gns")
    public UserGoalsAndStreaks getGoalsAndStreaks() {
        return appService.getUserGoalsAndStreaks();
    }

    @GetMapping("/settings")
    public UserSettings getSettings() {
        return appService.getUserSettings();
    }
}

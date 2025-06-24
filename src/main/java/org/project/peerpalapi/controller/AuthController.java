package org.project.peerpalapi.controller;

import lombok.AllArgsConstructor;
import org.project.peerpalapi.dto.auth.UserLoginDTO;
import org.project.peerpalapi.dto.auth.UserRegisterDTO;
import org.project.peerpalapi.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/peerpal/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        return authService.registerUser(userRegisterDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        return authService.loginUser(userLoginDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken() {
        return authService.refreshToken();
    }
}

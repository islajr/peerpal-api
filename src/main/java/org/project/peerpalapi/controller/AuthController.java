package org.project.peerpalapi.controller;

import lombok.AllArgsConstructor;
import org.project.peerpalapi.dto.auth.requests.UserLoginDTO;
import org.project.peerpalapi.dto.auth.requests.UserRegisterDTO;
import org.project.peerpalapi.dto.auth.requests.ConfirmationDTO;
import org.project.peerpalapi.dto.auth.requests.VerificationDTO;
import org.project.peerpalapi.dto.auth.responses.EmailConfirmResponseDTO;
import org.project.peerpalapi.dto.auth.responses.TokenResponseDTO;
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
    public ResponseEntity<EmailConfirmResponseDTO> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        return authService.registerUser(userRegisterDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        return authService.loginUser(userLoginDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDTO> refreshToken() {
        return authService.refreshToken();
    }

    @PostMapping("/confirm")
    public ResponseEntity<TokenResponseDTO> confirmAction(@RequestBody VerificationDTO verificationDTO) {
        return authService.confirm(verificationDTO.email(), verificationDTO.code());
    }

    @PostMapping("/verify")
    public ResponseEntity<EmailConfirmResponseDTO> verifyEmail(@RequestBody ConfirmationDTO confirmationDTO) {
        return authService.verify(confirmationDTO.email());
    }
}

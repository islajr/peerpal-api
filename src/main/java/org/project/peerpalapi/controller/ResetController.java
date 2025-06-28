package org.project.peerpalapi.controller;

import lombok.AllArgsConstructor;
import org.project.peerpalapi.dto.reset.requests.EmailResetDTO;
import org.project.peerpalapi.dto.reset.requests.PasswordResetDTO;
import org.project.peerpalapi.dto.reset.requests.ResetDTO;
import org.project.peerpalapi.dto.reset.requests.UsernameResetDTO;
import org.project.peerpalapi.dto.reset.responses.ResetResponse;
import org.project.peerpalapi.service.ResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/peerpal/reset")
@RestController
@AllArgsConstructor
public class ResetController {

    private final ResetService resetService;

    @PutMapping("/password")
    public ResponseEntity<ResetResponse> passwordReset(@RequestBody PasswordResetDTO passwordResetDTO) {
        return resetService.passwordReset(passwordResetDTO);
    }

    @PutMapping("/username")
    public ResponseEntity<ResetResponse> usernameReset(@RequestBody UsernameResetDTO usernameResetDTO) {
        return resetService.usernameReset(usernameResetDTO);
    }

    @PutMapping("/email")
    public ResponseEntity<ResetResponse> emailReset(@RequestBody EmailResetDTO emailResetDTO) {
        return resetService.emailReset(emailResetDTO);
    }

    @PostMapping("/verify")
    public ResponseEntity<ResetResponse> verifyPassword(@RequestParam String action, @RequestBody ResetDTO resetDTO) {
        return resetService.verify(action, resetDTO.code());
    }
}

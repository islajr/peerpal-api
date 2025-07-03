package org.project.peerpalapi.controller;

import lombok.AllArgsConstructor;
import org.project.peerpalapi.dto.reset.requests.EmailResetDTO;
import org.project.peerpalapi.dto.reset.requests.PasswordResetDTO;
import org.project.peerpalapi.dto.reset.requests.ResetDTO;
import org.project.peerpalapi.dto.reset.requests.UsernameResetDTO;
import org.project.peerpalapi.dto.reset.responses.ResetResponseDTO;
import org.project.peerpalapi.service.ResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/peerpal/reset")
@RestController
@AllArgsConstructor
public class ResetController {

    private final ResetService resetService;

    @PatchMapping("/password")
    public ResponseEntity<ResetResponseDTO> passwordReset(@RequestBody PasswordResetDTO passwordResetDTO) {
        return resetService.passwordReset(passwordResetDTO);
    }

    @PatchMapping("/username")
    public ResponseEntity<ResetResponseDTO> usernameReset(@RequestBody UsernameResetDTO usernameResetDTO) {
        return resetService.usernameReset(usernameResetDTO);
    }

    @PatchMapping("/email")
    public ResponseEntity<ResetResponseDTO> emailReset(@RequestBody EmailResetDTO emailResetDTO) {
        return resetService.emailReset(emailResetDTO);
    }

    @PatchMapping("/verify")
    public ResponseEntity<ResetResponseDTO> verifyPassword(@RequestParam String action, @RequestBody ResetDTO resetDTO) {
        return resetService.verify(action, resetDTO.code());
    }
}

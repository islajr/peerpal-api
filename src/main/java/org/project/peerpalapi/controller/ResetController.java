package org.project.peerpalapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.project.peerpalapi.dto.reset.requests.EmailResetDTO;
import org.project.peerpalapi.dto.reset.requests.FullNameResetDTO;
import org.project.peerpalapi.dto.reset.requests.PasswordResetDTO;
import org.project.peerpalapi.dto.reset.requests.ResetDTO;
import org.project.peerpalapi.dto.reset.responses.ResetResponseDTO;
import org.project.peerpalapi.service.ResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/peerpal/reset")
@RestController
@AllArgsConstructor
@Tag(name = "Authentication", description = "This documentation explains how authentication has been implemented within this API.")
public class ResetController {

    private final ResetService resetService;

    @Operation(description = "This endpoint lets users update their password", summary = "Authentication and Authorization is required.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent confirmation e-mail"),
            @ApiResponse(responseCode = "500", description = "Error sending confirmation e-mail")
    })
    @PatchMapping("/password")
    public ResponseEntity<ResetResponseDTO> passwordReset(@RequestBody PasswordResetDTO passwordResetDTO) {
        return resetService.passwordReset(passwordResetDTO);
    }

    @Operation(description = "This endpoint lets users update their username", summary = "Authentication is required.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated username"),
            @ApiResponse(responseCode = "409", description = "Username is taken")
    })
    @PatchMapping("/name")
    public ResponseEntity<ResetResponseDTO> fullNameReset(@RequestBody FullNameResetDTO fullNameResetDTO) {
        return resetService.fullNameReset(fullNameResetDTO);
    }

    @Operation(description = "This endpoint lets users update their e-mail", summary = "Authentication and Authorization is required.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent confirmation e-mail"),
            @ApiResponse(responseCode = "409", description = "E-mail already exists"),
            @ApiResponse(responseCode = "500", description = "Error sending confirmation e-mail")
    })
    @PatchMapping("/email")
    public ResponseEntity<ResetResponseDTO> emailReset(@RequestBody EmailResetDTO emailResetDTO) {
        return resetService.emailReset(emailResetDTO);
    }

    @Operation(description = "This is an intermediary endpoint that services all e-mail confirmatory actions", summary = "Authentication and Authorization is required.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sent confirmation e-mail"),
            @ApiResponse(responseCode = "500", description = "Error sending confirmation e-mail"),
            @ApiResponse(responseCode = "400", description = "Unexpected action")
    })
    @Parameters(value = {
            @Parameter(name = "action", required = true, description = "describes the action being confirmed.", examples = {
                    @ExampleObject(name = "password", description = "implies this is a confirmatory action for a password reset"),
                    @ExampleObject(name = "email", description = "implies that this is a confirmatory action for an e-mail reset")
            })
    })
    @PatchMapping("/verify")
    public ResponseEntity<ResetResponseDTO> verify(@RequestParam String action, @RequestBody ResetDTO resetDTO) {
        return resetService.verify(action, resetDTO.code());
    }
}

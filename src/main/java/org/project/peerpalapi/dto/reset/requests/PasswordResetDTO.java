package org.project.peerpalapi.dto.reset.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PasswordResetDTO(

        @NotNull(message = "password field is required")
        @Size(min = 8, max = 75)
        String password
) {
}

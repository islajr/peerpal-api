package org.project.peerpalapi.dto.reset.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FullNameResetDTO(

        @NotNull(message = "full name field is required.")
        @NotBlank(message = "full name field is required.")
        @Size(min = 1)
        String name
) {
}

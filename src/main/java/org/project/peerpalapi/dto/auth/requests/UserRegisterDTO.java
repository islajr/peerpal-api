package org.project.peerpalapi.dto.auth.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.project.peerpalapi.entity.User;

public record UserRegisterDTO(

        @NotNull
        @NotBlank(message = "your full name is required")
        @Size(min = 1)
        String fullName,

        @NotNull
        @NotBlank(message = "an e-mail address is required")
        @Email(message = "provide a valid e-mail address")
        @Size(min = 5, max = 50)
        String email,

        @NotNull
        @NotBlank(message = "a password is required")
        @Size(min = 8, max = 75)
        String password
) {

    public static User toUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setFullName(userRegisterDTO.fullName);
        user.setEmail(userRegisterDTO.email);
        user.setPassword(userRegisterDTO.password);
        return user;
    }
}

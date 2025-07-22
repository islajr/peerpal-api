package org.project.peerpalapi.util;

import lombok.RequiredArgsConstructor;
import org.project.peerpalapi.entity.User;
import org.project.peerpalapi.entity.UserPrincipal;
import org.project.peerpalapi.repository.AuthRepository;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class Utilities {

    private final AuthRepository authRepository;

    public static String sortIdentifier(String identifier) {
        if (!identifier.isBlank()){

            if (identifier.contains("@") && identifier.endsWith(".com")) {  // should fine-tune this later.
                return "email";
            }

            return "username";
        }

        return null;
    }

}

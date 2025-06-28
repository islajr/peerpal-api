package org.project.peerpalapi.service;

import lombok.AllArgsConstructor;
import org.project.peerpalapi.entity.User;
import org.project.peerpalapi.entity.UserPrincipal;
import org.project.peerpalapi.exceptions.auth.AuthException;
import org.project.peerpalapi.repository.AuthRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.project.peerpalapi.util.Utilities.sortIdentifier;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = null;

        switch (sortIdentifier(identifier)) {
            case "email" -> user = authRepository.findUserByEmail(identifier);
            case "username" -> user = authRepository.findUserByUsername(identifier);
            case null -> throw new NullPointerException("blank username");  // customize exception
            default -> throw new IllegalStateException("unexpected value: " + identifier);  // same as above.
        }

        // second stage of checking
        if (user == null && Objects.equals(sortIdentifier(identifier), "email")) {
            user = authRepository.findUserByUsername(identifier);
        } else if (user == null && Objects.equals(sortIdentifier(identifier), "username")) {
            user = authRepository.findUserByEmail(identifier);
        }

        // finally
        if (user == null) {
            throw new AuthException(404, "no such user.");
        }

        return new UserPrincipal(user);
    }
}

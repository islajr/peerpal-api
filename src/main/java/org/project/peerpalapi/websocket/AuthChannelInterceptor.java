package org.project.peerpalapi.websocket;

import lombok.RequiredArgsConstructor;
import org.project.peerpalapi.entity.User;
import org.project.peerpalapi.entity.UserPrincipal;
import org.project.peerpalapi.exceptions.auth.AuthException;
import org.project.peerpalapi.repository.AuthRepository;
import org.project.peerpalapi.service.CustomUserDetailsService;
import org.project.peerpalapi.service.JwtService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthChannelInterceptor implements ChannelInterceptor {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final AuthRepository authRepository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        // check if message is 'CONNECT'
        if (headerAccessor.getCommand() != null && headerAccessor.getCommand().equals(StompCommand.CONNECT)) {
            String authHeader = headerAccessor.getFirstNativeHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring("Bearer ".length());

                // token validation
                User user = validateAndGetUser(token);

                if (user != null) {

                    UserPrincipal userPrincipal = (UserPrincipal) customUserDetailsService.loadUserByUsername(user.getEmail());

                    headerAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

                    Authentication authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                    if (headerAccessor != null) {
                        headerAccessor.setUser(authentication);
                    } else {
                        throw new AuthException(401, "we don't know you");
                    }

                    // set authenticated user in spring security context
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    context.setAuthentication(authentication);
                    SecurityContextHolder.setContext(context);
                }
            }
        }

        return message;
    }

    User validateAndGetUser(String token) {
        String email = jwtService.extractEmail(token);
        UserPrincipal userPrincipal = (UserPrincipal) customUserDetailsService.loadUserByUsername(email);

        // load username into Principal for use in controller and service classes
        if (userPrincipal != null && jwtService.verifyToken(token, userPrincipal)) {
            return authRepository.findUserByEmail(email);
        }

        throw new AuthException(400, "invalid token");
    }
}

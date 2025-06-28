package org.project.peerpalapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.project.peerpalapi.entity.UserPrincipal;
import org.project.peerpalapi.service.CustomUserDetailsService;
import org.project.peerpalapi.service.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final List<String> PUBLIC_URLS = List.of(
            "/api/v1/peerpal/auth/register",
            "/api/v1/peerpal/auth/login",
            "/api/v1/peerpal/auth/verify",
            "/api/v1/peerpal/auth/confirm"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        final String AUTH_PREFIX = "Bearer ";
        String token = null;
        String email = null;
        String url = request.getRequestURI();

        if (PUBLIC_URLS.contains(url)){
            System.out.println("skipping jwt filter for path: " + url);
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader != null && authHeader.startsWith(AUTH_PREFIX)) {
            token = authHeader.substring(AUTH_PREFIX.length());
            email = jwtService.extractEmail(token);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserPrincipal userPrincipal = (UserPrincipal) customUserDetailsService.loadUserByUsername(email);

            if (jwtService.verifyToken(token, userPrincipal)) {
                UsernamePasswordAuthenticationToken authToken
                        = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } else {
            if (token != null)
                throw new BadCredentialsException("invalid token!");    // change to JwtException or custom later.
            else
                throw new BadRequestException("problematic request!");    // problem with the request
        }

        filterChain.doFilter(request, response);

    }
}

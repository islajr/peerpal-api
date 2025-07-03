package org.project.peerpalapi.config;

import lombok.AllArgsConstructor;
import org.project.peerpalapi.security.CustomCorsConfiguration;
import org.project.peerpalapi.security.CustomLogoutHandler;
import org.project.peerpalapi.security.CustomLogoutSuccessHandler;
import org.project.peerpalapi.security.JwtFilter;
import org.project.peerpalapi.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtFilter jwtFilter;
    private final CustomLogoutHandler customLogoutHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final AuthenticationEntryPoint authEntryPoint;
    private final CustomCorsConfiguration customCorsConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests.requestMatchers(
                        "/api/v1/peerpal/auth/**",
                                "/swagger-ui/**",               // Swagger UI static resources
                                "/v3/api-docs",
                                "/v3/api-docs/**",              // OpenAPI spec
                                "/swagger-ui.html",             // Swagger main page
                                "/webjars/**"

                ).permitAll()
                        .anyRequest().authenticated())
                .cors(c -> c.configurationSource(customCorsConfiguration))
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
                .logout(logout -> logout
                        .logoutUrl("/api/v1/peerpal/auth/logout")
                        .addLogoutHandler(customLogoutHandler)
                        .logoutSuccessHandler(customLogoutSuccessHandler)
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());
            if (!passwordEncoder().matches(authentication.getCredentials().toString(), userDetails.getPassword())){
                throw new BadCredentialsException("incorrect password!");
            }
            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        };
    }
}

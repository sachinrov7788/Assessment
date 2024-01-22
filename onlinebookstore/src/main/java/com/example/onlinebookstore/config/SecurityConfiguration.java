package com.example.onlinebookstore.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http.csrf(csrf -> csrf
                                .disable())
                                .authorizeHttpRequests(requests -> requests
                                                .requestMatchers("/api/auth/**")
                                                .permitAll() 
                                                .requestMatchers("/api/authors/**",
                                                "/api/books/**",
                                                "/api/inventories/**",
                                                "/api/orders/**",
                                                "/api/order-items/**",
                                                "/api/publishers/**"
                                                )
                                .hasAuthority("ADMIN")
                                                .requestMatchers("/api/authors/getauthorbyid/**",
                                                "/api/books/**",
                                                "/api/orders/**",
                                                "/api/order-items/**",
                                                "/api/publishers/**"
                                                )
                                .hasAuthority("VENDOR")

                                                .requestMatchers("/api/authors/**").hasAuthority("ADMIN")
                                                .anyRequest()
                                                .authenticated())
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
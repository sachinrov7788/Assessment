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
                                                .requestMatchers("/api/auth/**").permitAll() 
                                                .requestMatchers("/api/authors/delete/**",
                                                "/api/books/delete/**",
                                                "/api/inventories/delete/**",
                                                "/api/orders/delete/**",
                                                "/api/order-items/delete/**",
                                                "/api/publishers/delete/**",
                                                "/api/users/all",
                                                "/api/users/**",
                                                "/api/users/delete/**",
                                                "/api/users/by-role/**")
                                .hasAnyAuthority("ADMIN")
                                // .requestMatchers("/api/authors/**",
                                //                 "/api/books/**",
                                //                 "/api/orders/**",
                                //                 "/api/order-items/getbyorderitemid/**",
                                //                 "/api/order-items/by-book/**",
                                //                 "/api/publishers/getallpublisher",
                                //                 "/api/publishers/getpublisherbyid"
                                //                 )
                                // .hasAnyAuthority("ADMIN","VENDOR")
                                //                 .requestMatchers("/api/authors/getauthorbyid/**",
                                //                 "/api/authors/getallauthors",
                                //                 "/api/books/getallbooks",
                                //                 "/api/books/getbooksbyid/**",
                                //                 "/api/orders/**",
                                //                 "/api/order-items/getbyorderitemid/**",
                                //                 "/api/order-items/**",
                                //                 "/api/publishers/getallpublisher",
                                //                 "/api/publishers/getpublisherbyid"
                                //                 )
                                // .hasAnyAuthority("ADMIN","CUSTOMER")

                                                // .requestMatchers("/api/authors/**").hasAuthority("ADMIN")
                                                .anyRequest()
                                                .authenticated())
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
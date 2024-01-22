package com.example.onlinebookstore.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.onlinebookstore.model.AuthenticationRequest;
import com.example.onlinebookstore.model.AuthenticationResponse;
import com.example.onlinebookstore.model.RegisterRequest;
import com.example.onlinebookstore.model.Role;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.UserRepository;
import com.example.onlinebookstore.service.AuthenticationService;
import com.example.onlinebookstore.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AuthenticationServiceImpl implements AuthenticationService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
                // request.setPassword("cxvzcxbnvm,.kl");
                User user = User.builder()
                                .username(request.getUsername())
                                .storeName(request.getStoreName())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .build();

                if (user.getUsername().endsWith("_admin")) {
                        user.setRole(Role.ADMIN);
                } else if (user.getUsername().endsWith("_vendor")) {
                        user.setRole(Role.VENDOR);
                }
                else {
                        user.setRole(Role.CUSTOMER);
                }
                User savedUser;
                try {
                        if (userRepository.existsByUsername(user.getUsername())) {
                                throw new Exception("Username or email already exists.");
                        }
                        savedUser = userRepository.save(user);
                } catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }

                Map<String, Object> extraClaims = new HashMap<>();
                extraClaims.put("Authorities", user.getAuthorities());
                String jwtToken = jwtService.generateToken(savedUser);

                return new ResponseEntity<>(AuthenticationResponse.builder()
                                .token(jwtToken)
                                .user(savedUser)
                                .build(), HttpStatus.OK);
        }

        public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
                
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));

                User user = userRepository.findByUsername(request.getUsername())
                                .orElseThrow(() -> new UsernameNotFoundException("Username or Password invalid"));

                Map<String, Object> extraClaims = new HashMap<>();
                extraClaims.put("Authorities", user.getAuthorities());
                String jwtToken = jwtService.generateToken(extraClaims, user);
                return new ResponseEntity<>(AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build(), HttpStatus.OK);
        }
        
}
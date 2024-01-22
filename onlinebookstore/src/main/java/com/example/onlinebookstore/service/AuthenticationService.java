package com.example.onlinebookstore.service;

import org.springframework.http.ResponseEntity;

import com.example.onlinebookstore.model.AuthenticationRequest;
import com.example.onlinebookstore.model.AuthenticationResponse;
import com.example.onlinebookstore.model.RegisterRequest;

public interface AuthenticationService {
    
    ResponseEntity<AuthenticationResponse> register(RegisterRequest request);

    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request);
}
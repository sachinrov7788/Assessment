package com.example.onlinebookstore.model;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class AuthenticationResponse {
    private String token;
    private User user;
}
package com.example.onlinebookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
public class AuthenticationResponse {
    private String token;
    private User user;
}
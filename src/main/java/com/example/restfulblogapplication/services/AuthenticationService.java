package com.example.restfulblogapplication.services;

import com.example.restfulblogapplication.dtos.AuthenticationRequest;
import com.example.restfulblogapplication.dtos.RegisterRequest;
import org.springframework.http.ResponseEntity;

/**
 * Service class for handling user authentication and registration.
 */
public interface AuthenticationService {
  ResponseEntity<String> register(RegisterRequest request);

  ResponseEntity<String> authenticate(AuthenticationRequest request);
}

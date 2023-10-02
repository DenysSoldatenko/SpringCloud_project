package com.example.restfulblogapplication.services;

import com.example.restfulblogapplication.dtos.auth.AuthenticationRequest;
import com.example.restfulblogapplication.dtos.auth.AuthenticationResponse;
import com.example.restfulblogapplication.dtos.auth.RegisterRequest;

/**
 * Service class for handling user authentication and registration.
 */
public interface AuthenticationService {
  AuthenticationResponse register(RegisterRequest request);

  AuthenticationResponse authenticate(AuthenticationRequest request);
}

package com.example.restfulblogapplication.services;

import com.example.restfulblogapplication.dtos.AuthenticationRequest;
import com.example.restfulblogapplication.dtos.AuthenticationResponse;
import com.example.restfulblogapplication.dtos.RegisterRequest;

/**
 * Service class for handling user authentication and registration.
 */
public interface AuthenticationService {
  AuthenticationResponse register(RegisterRequest request);

  AuthenticationResponse authenticate(AuthenticationRequest request);
}

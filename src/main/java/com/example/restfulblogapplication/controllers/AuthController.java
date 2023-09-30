package com.example.restfulblogapplication.controllers;

import com.example.restfulblogapplication.dtos.AuthenticationRequest;
import com.example.restfulblogapplication.dtos.AuthenticationResponse;
import com.example.restfulblogapplication.dtos.RegisterRequest;
import com.example.restfulblogapplication.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling authentication-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthenticationService authenticationService;

  @PostMapping("/authenticate")
  public AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest request) {
    return authenticationService.authenticate(request);
  }

  @PostMapping("/register")
  public AuthenticationResponse registerUser(@RequestBody RegisterRequest request) {
    return authenticationService.register(request);
  }
}

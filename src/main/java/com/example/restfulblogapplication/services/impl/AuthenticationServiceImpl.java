package com.example.restfulblogapplication.services.impl;

import com.example.restfulblogapplication.dtos.AuthenticationRequest;
import com.example.restfulblogapplication.dtos.RegisterRequest;
import com.example.restfulblogapplication.entities.User;
import com.example.restfulblogapplication.repositories.UserRepository;
import com.example.restfulblogapplication.services.AuthenticationService;
import com.example.restfulblogapplication.utils.UserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service implementation for handling user authentication and registration.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final UserFactory userFactory;

  /**
   * Registers a new user based on the provided registration request.
   *
   * @param request The registration request containing user details.
   * @return AuthenticationResponse containing the JWT token.
   */
  public ResponseEntity<String> register(RegisterRequest request) {

    if (userRepository.findByEmail(request.email()).isPresent()) {
      return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
    }

    User user = userFactory.createUserFromRequest(request);
    userRepository.save(user);

    return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
  }

  /**
   * Authenticates a user based on the provided authentication request.
   *
   * @param request The authentication request containing user credentials.
   * @return AuthenticationResponse containing the JWT token.
   */
  public ResponseEntity<String> authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );

    userRepository.findByEmail(request.email())
        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

    return new ResponseEntity<>("User signed-in successfully", HttpStatus.OK);
  }
}
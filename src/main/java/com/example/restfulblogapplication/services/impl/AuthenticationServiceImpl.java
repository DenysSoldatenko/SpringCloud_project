package com.example.restfulblogapplication.services.impl;

import static com.example.restfulblogapplication.utils.ApplicationConstant.EMAIL_ALREADY_TAKEN;

import com.example.restfulblogapplication.dtos.AuthenticationRequest;
import com.example.restfulblogapplication.dtos.AuthenticationResponse;
import com.example.restfulblogapplication.dtos.RegisterRequest;
import com.example.restfulblogapplication.entities.User;
import com.example.restfulblogapplication.exceptions.ConflictException;
import com.example.restfulblogapplication.repositories.UserRepository;
import com.example.restfulblogapplication.security.jwt.JwtService;
import com.example.restfulblogapplication.services.AuthenticationService;
import com.example.restfulblogapplication.utils.UserFactory;
import lombok.RequiredArgsConstructor;
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
  private final JwtService jwtService;

  /**
   * Registers a new user based on the provided registration request.
   *
   * @param request The registration request containing user details.
   * @return AuthenticationResponse containing the JWT token.
   */
  public AuthenticationResponse register(RegisterRequest request) {

    if (userRepository.findByEmail(request.email()).isPresent()) {
      throw new ConflictException(EMAIL_ALREADY_TAKEN);
    }

    User user = userFactory.createUserFromRequest(request);
    userRepository.save(user);

    return new AuthenticationResponse("User registered successfully!");
  }

  /**
   * Authenticates a user based on the provided authentication request.
   *
   * @param request The authentication request containing user credentials.
   * @return AuthenticationResponse containing the JWT token.
   */
  public AuthenticationResponse authenticate(AuthenticationRequest request) {

    User user = userRepository.findByEmail(request.email())
        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );

    String jwtToken = jwtService.generateToken(user);
    return new AuthenticationResponse(jwtToken);
  }
}
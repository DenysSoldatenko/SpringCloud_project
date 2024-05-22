package org.example.gatewayservice.utils;

import lombok.RequiredArgsConstructor;
import org.example.gatewayservice.dtos.RegisterRequestDto;
import org.example.gatewayservice.entities.Role;
import org.example.gatewayservice.entities.User;
import org.springframework.stereotype.Component;

/**
 * Factory class for creating User instances from registration requests.
 */
@Component
@RequiredArgsConstructor
public class UserFactory {

  private final Pbkdf2Encoder passwordEncoder;

  /**
   * Creates a new User instance from a registration request.
   *
   * @param request The registration request containing user information.
   * @return A new User instance.
   */
  public User createUserFromRequest(RegisterRequestDto request) {
    return User.builder()
    .firstName(request.firstName())
    .lastName(request.lastName())
    .email(request.email())
    .password(passwordEncoder.encode(request.password()))
    .role(Role.USER)
    .build();
  }
}
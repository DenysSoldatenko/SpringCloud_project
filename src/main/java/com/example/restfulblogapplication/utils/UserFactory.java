package com.example.restfulblogapplication.utils;


import com.example.restfulblogapplication.dtos.RegisterRequest;
import com.example.restfulblogapplication.entities.Role;
import com.example.restfulblogapplication.entities.User;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Factory class for creating User instances from registration requests.
 */
@Component
@RequiredArgsConstructor
public class UserFactory {

  private final PasswordEncoder passwordEncoder;

  /**
   * Creates a new User instance from a registration request.
   *
   * @param request The registration request containing user information.
   * @return A new User instance.
   */
  public User createUserFromRequest(RegisterRequest request) {
    return User.builder()
    .firstName(request.firstName())
    .lastName(request.lastName())
    .email(request.email())
    .password(passwordEncoder.encode(request.password()))
    .roles(Collections.singleton(Role.USER))
    .build();
  }
}
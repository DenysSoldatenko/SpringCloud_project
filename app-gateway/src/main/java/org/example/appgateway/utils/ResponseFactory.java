package org.example.appgateway.utils;

import lombok.RequiredArgsConstructor;
import org.example.appgateway.dtos.AuthResponseDto;
import org.example.appgateway.entities.User;
import org.example.appgateway.jwt.JwtService;
import org.springframework.stereotype.Component;

/**
 * Utility class for creating responses.
 */
@Component
@RequiredArgsConstructor
public class ResponseFactory {

  private final JwtService jwtService;

  /**
   * Creates an authentication response DTO from a user.
   *
   * @param user The user for whom the authentication response is created.
   * @return An AuthResponseDto containing user and token details.
   */
  public AuthResponseDto createResponseFromUser(User user) {
    return AuthResponseDto.builder()
      .userId(user.getId())
      .token(jwtService.generateToken(user).getToken())
      .issuedAt(jwtService.generateToken(user).getIssuedAt())
      .expiresAt(jwtService.generateToken(user).getExpiresAt())
      .build();
  }
}
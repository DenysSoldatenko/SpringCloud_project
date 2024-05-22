package org.example.gatewayservice.services;


import org.example.gatewayservice.dtos.AuthRequestDto;
import org.example.gatewayservice.dtos.AuthResponseDto;
import org.example.gatewayservice.dtos.RegisterRequestDto;
import reactor.core.publisher.Mono;

/**
 * Service class for handling user authentication and registration.
 */
public interface AuthService {
  Mono<String> registerUser(RegisterRequestDto request);

  Mono<AuthResponseDto> authenticateUser(AuthRequestDto request);
}

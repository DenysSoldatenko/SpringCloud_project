package org.example.appgateway.services;


import org.example.appgateway.dtos.AuthRequestDto;
import org.example.appgateway.dtos.AuthResponseDto;
import org.example.appgateway.dtos.RegisterRequestDto;
import reactor.core.publisher.Mono;

/**
 * Service class for handling user authentication and registration.
 */
public interface AuthService {
  Mono<String> registerUser(RegisterRequestDto request);

  Mono<AuthResponseDto> authenticateUser(AuthRequestDto request);
}

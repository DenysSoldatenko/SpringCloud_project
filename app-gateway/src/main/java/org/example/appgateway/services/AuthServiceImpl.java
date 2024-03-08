package org.example.appgateway.services;

import static reactor.core.publisher.Mono.error;
import static reactor.core.publisher.Mono.just;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.appcommon.exceptions.AuthException;
import org.example.appgateway.dtos.AuthRequestDto;
import org.example.appgateway.dtos.AuthResponseDto;
import org.example.appgateway.dtos.RegisterRequestDto;
import org.example.appgateway.entities.User;
import org.example.appgateway.repositories.UserRepository;
import org.example.appgateway.utils.Pbkdf2Encoder;
import org.example.appgateway.utils.ResponseFactory;
import org.example.appgateway.utils.UserFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Service implementation for user authentication and registration.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;
  private final Pbkdf2Encoder passwordEncoder;
  private final ResponseFactory responseFactory;
  private final UserFactory userFactory;

  @Override
  public Mono<String> registerUser(RegisterRequestDto request) {
    User user = userFactory.createUserFromRequest(request);
    return userRepository.save(user)
      .doOnSuccess(u -> log.info("IN registerUser - user: {} created", u))
      .thenReturn("User registered successfully!");
  }

  @Override
  public Mono<AuthResponseDto> authenticateUser(AuthRequestDto request) {
    return userRepository.findByEmail(request.email())
      .flatMap(user -> authenticateUser(user, request))
      .switchIfEmpty(error(new AuthException("Invalid username!")));
  }

  private Mono<AuthResponseDto> authenticateUser(User user, AuthRequestDto request) {
    if (!passwordEncoder.matches(request.password(), user.getPassword())) {
      log.warn("Invalid password for user: {}", request.email());
      return error(new AuthException("Invalid password!"));
    }

    log.info("User details - ID: {}, Role: {}", user.getId(), user.getRole());

    AuthResponseDto authResponseDto = responseFactory.createResponseFromUser(user);
    return just(authResponseDto);
  }
}

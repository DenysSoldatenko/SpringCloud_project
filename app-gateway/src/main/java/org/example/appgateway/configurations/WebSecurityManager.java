package org.example.appgateway.configurations;

import static reactor.core.publisher.Mono.error;

import lombok.RequiredArgsConstructor;
import org.example.appcommon.exceptions.AuthException;
import org.example.appgateway.jwt.common.CustomPrincipal;
import org.example.appgateway.repositories.UserRepository;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Custom implementation of ReactiveAuthenticationManager for WebFlux security.
 */
@Component
@RequiredArgsConstructor
public class WebSecurityManager implements ReactiveAuthenticationManager {

  private final UserRepository userService;

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
    return userService.findById(principal.getId())
      .switchIfEmpty(error(new AuthException("User not found!")))
      .map(user -> authentication);
  }
}
package org.example.appgateway.controllers;

import lombok.RequiredArgsConstructor;
import org.example.appgateway.dtos.AuthRequestDto;
import org.example.appgateway.dtos.AuthResponseDto;
import org.example.appgateway.dtos.RegisterRequestDto;
import org.example.appgateway.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Controller class for handling authentication-related requests.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public Mono<ResponseEntity<String>> register(@RequestBody RegisterRequestDto request) {
    return authService.registerUser(request)
      .map(result -> ResponseEntity.status(HttpStatus.CREATED).body(result));
  }

  @PostMapping("/authenticate")
  public Mono<ResponseEntity<AuthResponseDto>> login(@RequestBody AuthRequestDto request) {
    return authService.authenticateUser(request)
      .map(result -> ResponseEntity.status(HttpStatus.OK).body(result));
  }
}
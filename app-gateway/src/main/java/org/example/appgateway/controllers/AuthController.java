package org.example.appgateway.controllers;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.appgateway.dtos.AuthRequestDto;
import org.example.appgateway.dtos.AuthResponseDto;
import org.example.appgateway.dtos.RegisterRequestDto;
import org.example.appgateway.services.AuthService;
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
@Tag(name = "Auth Controller", description = "Handles authentication and registration requests")
public class AuthController {

  private final AuthService authService;

  @Operation(summary = "Register a new user", description = "Endpoint to register a new user")
  @ApiResponses(value = {
      @ApiResponse(
        responseCode = "201", description = "User registered successfully",
        content = @Content(mediaType = "text/plain")
      ),
      @ApiResponse(
        responseCode = "400", description = "Invalid input data",
        content = @Content(mediaType = "application/json")
      ),
      @ApiResponse(
        responseCode = "500", description = "Internal server error",
        content = @Content(mediaType = "application/json")
      )
  })
  @PostMapping("/register")
  public Mono<ResponseEntity<String>> register(@RequestBody RegisterRequestDto request) {
    return authService.registerUser(request).map(result -> status(CREATED).body(result));
  }

  @Operation(summary = "Authenticate a user", description = "Endpoint to authenticate a user")
  @ApiResponses(value = {
      @ApiResponse(
        responseCode = "200", description = "User authenticated successfully",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = AuthResponseDto.class))
      ),
      @ApiResponse(
        responseCode = "400", description = "Bad Request",
        content = @Content(mediaType = "application/json")
      ),
      @ApiResponse(
        responseCode = "401", description = "Invalid credentials",
        content = @Content(mediaType = "application/json")
      ),
      @ApiResponse(
        responseCode = "404", description = "User not found",
        content = @Content(mediaType = "application/json")
      ),
      @ApiResponse(
        responseCode = "500", description = "Internal server error",
        content = @Content(mediaType = "application/json")
      )
  })
  @PostMapping("/authenticate")
  public Mono<ResponseEntity<AuthResponseDto>> login(@RequestBody AuthRequestDto request) {
    return authService.authenticateUser(request).map(result -> status(OK).body(result));
  }
}
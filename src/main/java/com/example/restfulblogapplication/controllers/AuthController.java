package com.example.restfulblogapplication.controllers;

import com.example.restfulblogapplication.dtos.AuthenticationRequest;
import com.example.restfulblogapplication.dtos.AuthenticationResponse;
import com.example.restfulblogapplication.dtos.RegisterRequest;
import com.example.restfulblogapplication.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling authentication-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication Controller", description = "Handles authentication related operations")
public class AuthController {

  private final AuthenticationService authenticationService;

  @Operation(summary = "Authenticate a user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful authentication",
      content = {
          @Content(mediaType = "application/json",
          schema = @Schema(implementation = AuthenticationResponse.class))
      }),
      @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PostMapping("/authenticate")
  public AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest request) {
    return authenticationService.authenticate(request);
  }

  @Operation(summary = "Register a user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successful registration",
      content = {
          @Content(mediaType = "application/json",
          schema = @Schema(implementation = AuthenticationResponse.class))
      }),
      @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
      @ApiResponse(responseCode = "409", description = "Conflict - Email already taken", content = @Content),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public AuthenticationResponse registerUser(@RequestBody RegisterRequest request) {
    return authenticationService.register(request);
  }
}

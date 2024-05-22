package org.example.gatewayservice.jwt.common;

import java.security.Principal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a custom implementation of the Principal interface for JWT authentication.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomPrincipal implements Principal {
  private Long id;
  private String name;
}
package org.example.gatewayservice.jwt.common;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data class representing details of a JWT token.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TokenDetails {
  private Long userId;
  private String token;
  private Date issuedAt;
  private Date expiresAt;
}
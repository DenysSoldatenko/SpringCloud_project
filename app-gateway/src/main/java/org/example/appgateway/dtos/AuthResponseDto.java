package org.example.appgateway.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Date;
import lombok.Builder;

/**
 * Data Transfer Object (DTO) for authentication response.
 */
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AuthResponseDto(Long userId, String token,
                              Date issuedAt, Date expiresAt) {
}

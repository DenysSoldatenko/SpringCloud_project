package org.example.appgateway.jwt;

import static io.jsonwebtoken.Jwts.parser;
import static io.jsonwebtoken.io.Decoders.BASE64;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static reactor.core.publisher.Mono.defer;
import static reactor.core.publisher.Mono.just;

import io.jsonwebtoken.Claims;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.appgateway.exceptions.AuthException;
import org.example.appgateway.jwt.common.VerificationResult;
import reactor.core.publisher.Mono;

/**
 * Handles the verification of JWT tokens.
 */
@RequiredArgsConstructor
public class JwtHandler {

  private final String secretKey;

  /**
   * Verifies the given JWT access token.
   *
   * @param accessToken The JWT access token to be verified.
   * @return A Mono emitting a VerificationResult containing the claims and the token
   *     if verification is successful.
   */
  @SneakyThrows
  public Mono<VerificationResult> check(String accessToken) {
    return defer(() -> {
      Claims claims = verify(accessToken);
      return just(new VerificationResult(claims, accessToken));
    });
  }

  private Claims verify(String token) {
    Claims claims = getClaimsFromToken(token);
    final Date expirationDate = claims.getExpiration();

    if (expirationDate.before(new Date())) {
      throw new AuthException("Token expired");
    }

    return claims;
  }

  private Claims getClaimsFromToken(String token) {
    return parser()
      .verifyWith(getSignInKey())
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }

  private SecretKey getSignInKey() {
    byte[] keyBytes = BASE64.decode(secretKey);
    return hmacShaKeyFor(keyBytes);
  }
}

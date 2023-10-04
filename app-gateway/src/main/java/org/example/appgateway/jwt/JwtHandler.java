package org.example.appgateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.appcommon.exceptions.AuthException;
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
    return Mono.defer(() -> {
      Claims claims = verify(accessToken);
      return Mono.just(new VerificationResult(claims, accessToken));
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
    return Jwts.parser()
      .verifyWith(getSignInKey())
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }

  private SecretKey getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}

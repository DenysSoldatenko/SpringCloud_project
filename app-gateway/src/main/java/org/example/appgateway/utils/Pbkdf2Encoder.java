package org.example.appgateway.utils;

import static java.util.Base64.getEncoder;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Custom implementation of PasswordEncoder using PBKDF2 with HMAC SHA-512.
 */
@Component
public class Pbkdf2Encoder implements PasswordEncoder {

  private static final String SECRET_KEY_INSTANCE = "PBKDF2WithHmacSHA512";

  @Value("${jwt.password.encoder.secret}")
  private String secret;

  @Value("${jwt.password.encoder.iteration}")
  private Integer iteration;

  @Value("${jwt.password.encoder.length}")
  private Integer length;

  @Override
  @SneakyThrows
  public String encode(CharSequence rawPassword) {

    byte[] result = SecretKeyFactory
        .getInstance(SECRET_KEY_INSTANCE)
        .generateSecret(
          new PBEKeySpec(rawPassword.toString().toCharArray(), secret.getBytes(), iteration, length)
        )
        .getEncoded();

    return getEncoder().encodeToString(result);
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return encode(rawPassword).equals(encodedPassword);
  }
}
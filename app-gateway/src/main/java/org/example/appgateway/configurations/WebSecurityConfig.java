package org.example.appgateway.configurations;

import lombok.extern.slf4j.Slf4j;
import org.example.appgateway.jwt.JwtConverter;
import org.example.appgateway.jwt.JwtHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import reactor.core.publisher.Mono;

/**
 * Configuration class for WebFlux security.
 */
@Slf4j
@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class WebSecurityConfig {

  @Value("${jwt.secret}")
  private String secret;

  private final String[] publicRoutes = {
    "/api/v*/auth/**",
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/swagger-resources/**",
    "/swagger-ui.html",
    "/webjars/**"
  };

  /**
   * Configures the security filters and rules.
   *
   * @param http The ServerHttpSecurity to configure.
   * @param webSecurityManager The WebSecurityManager for handling authentication.
   * @return The configured SecurityWebFilterChain.
   */
  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                       WebSecurityManager webSecurityManager) {
    return http
      .csrf(ServerHttpSecurity.CsrfSpec::disable)
      .authorizeExchange(
        authorizeExchangeSpec ->
          authorizeExchangeSpec.pathMatchers(HttpMethod.OPTIONS).permitAll()
            .pathMatchers(publicRoutes).permitAll()
            .anyExchange().authenticated()
      )
      .exceptionHandling(exceptionHandlingSpec ->
        exceptionHandlingSpec.authenticationEntryPoint(
            (swe, e) -> {
              log.error("IN securityWebFilterChain - unauthorized error: {}", e.getMessage());
              return Mono.fromRunnable(
                () -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)
              );
            })
          .accessDeniedHandler((swe, e) -> {
            log.error("IN securityWebFilterChain - access denied: {}", e.getMessage());

            return Mono.fromRunnable(
              () -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)
            );
          }))
      .addFilterAt(
        bearerAuthenticationFilter(webSecurityManager),
        SecurityWebFiltersOrder.AUTHENTICATION
      )
      .build();
  }

  private AuthenticationWebFilter bearerAuthenticationFilter(
      WebSecurityManager webSecurityManager
  ) {

    AuthenticationWebFilter bearerAuthenticationFilter
        = new AuthenticationWebFilter(webSecurityManager);

    bearerAuthenticationFilter.setServerAuthenticationConverter(
        new JwtConverter(new JwtHandler(secret)));

    bearerAuthenticationFilter.setRequiresAuthenticationMatcher(
        ServerWebExchangeMatchers.pathMatchers("/**"));

    return bearerAuthenticationFilter;
  }
}
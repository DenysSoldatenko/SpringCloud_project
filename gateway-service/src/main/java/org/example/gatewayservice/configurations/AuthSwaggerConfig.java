package org.example.gatewayservice.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 */
@Configuration
@OpenAPIDefinition(
    info = @Info(
      contact = @Contact(
        name = "Denys Soldatenko",
        email = "john.doe@example.com",
        url = "https://example.com/about"
      ),
      title = "Blog Rest API",
      version = "1.0",
      description = "Blog Rest API Information",
      license = @License(
        name = "Example License",
        url = "https://example.com"
      ),
      termsOfService = "https://example.com/terms"
    ),
    servers = {
        @Server(
          url = "http://localhost:8765",
          description = "Local server"
        )
    }
)
public class AuthSwaggerConfig {

}

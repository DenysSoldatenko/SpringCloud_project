package org.example.postservice.configurations;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
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
          url = "http://localhost:8081",
          description = "Local server"
        )
    }
)
@SecurityScheme(
    name = "Bearer Authentication",
    type = HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class BlogSwaggerConfig {

}

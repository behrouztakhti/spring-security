package com.behrouztakhti.security.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * This class is Configuration class in which we are able to create spring doc and swagger ui in order to documentation.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */

@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Security Role-based Authorization",
                description = "OpenApi documentation for Spring Boot Security Role-based Authorization",
                version = "1.0",
                termsOfService = "Terms of service",
                contact = @Contact(
                        name = "behrouz takhti",
                        email = "behrouz.takhti@gmail.com"
                )
        ),
        servers = {
                @Server(url = "http://localhost:3030", description = "Local environment")
        },
        security = {
                @SecurityRequirement(name = "BearerAuth")
        }
)
@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        description = "JWT Authentication",
        scheme = "Bearer",
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class OpenApiConfig {
}

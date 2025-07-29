package com.influencermarket.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI Configuration for Influencer Marketing API
 */
@OpenAPIDefinition(
    info = @Info(
        title = "Influencer Marketing API", 
        version = "${STAGE:1.0.0}",
        description = "Backend API for Influencer Marketing Platform"
    ), 
    servers = {
        @Server(url = "http://localhost:8080/api", description = "Local Development server"),
        @Server(url = "https://api.influencermarket.com/api", description = "Production server")
    }
)
@Configuration
public class OpenApiConfig {
}
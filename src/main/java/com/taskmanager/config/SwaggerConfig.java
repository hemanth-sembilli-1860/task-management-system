package com.taskmanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
    info = @Info(
        title = "Task Management API",
        version = "1.0",
        description = "Spring Boot Task Management System with JWT Authentication"
    )
)

@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)

public class SwaggerConfig {
	
}
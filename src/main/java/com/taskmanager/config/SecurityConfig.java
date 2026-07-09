package com.taskmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.taskmanager.security.JwtFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	                "/users/**",
	                "/swagger-ui.html",
	                "/swagger-ui/**",
	                "/v3/api-docs",
	                "/v3/api-docs/**"
	            ).permitAll()
	            .anyRequest().authenticated()
	        )
	        .sessionManagement(session ->
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
	        .exceptionHandling(ex -> ex
	        	    .authenticationEntryPoint((request, response, authException) -> {
	        	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	        	    })
	        	)
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
package com.taskmanager.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.taskmanager.util.Jwtutil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	@Autowired
	private Jwtutil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain)
	        throws ServletException, IOException {

	    if (request.getServletPath().startsWith("/swagger-ui") ||
	        request.getServletPath().startsWith("/v3/api-docs")) {
	        filterChain.doFilter(request, response);
	        return;
	    }

	    String authHeader = request.getHeader("Authorization");

	    if (authHeader != null && authHeader.startsWith("Bearer ")) {
	        try {

	            String token = authHeader.substring(7);
	            String email = jwtUtil.extractMail(token);

	            UsernamePasswordAuthenticationToken authToken =
	                    new UsernamePasswordAuthenticationToken(
	                            email,
	                            null,
	                            Collections.emptyList()
	                    );

	            SecurityContextHolder.getContext().setAuthentication(authToken);

	        } catch (Exception e) {
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
	            return;
	        }
	    }

	    filterChain.doFilter(request, response);
	}
}

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
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)
			throws ServletException,IOException{
		String authHeader = request.getHeader("Authorization");
		if (authHeader!=null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			String email = jwtUtil.extractMail(token);

			UsernamePasswordAuthenticationToken authToken =
			        new UsernamePasswordAuthenticationToken(
			                email,
			                null,
			                Collections.emptyList()
			        );

			SecurityContextHolder.getContext().setAuthentication(authToken);

			System.out.println("Valid token for : " + email);
		}
		filterChain.doFilter(request, response);
	}
}

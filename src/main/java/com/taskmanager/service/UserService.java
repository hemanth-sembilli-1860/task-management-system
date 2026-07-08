package com.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.taskmanager.dto.LoginRequest;
import com.taskmanager.dto.RegisterRequest;
import com.taskmanager.entity.Role;
import com.taskmanager.entity.User;
import com.taskmanager.repository.UserRepository;
import com.taskmanager.util.Jwtutil;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	public User registerUser(RegisterRequest request) {
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
		    throw new IllegalStateException("Email already exists");
		}
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(Role.USER);
		return userRepository.save(user);
	}
	public List<User> getAllUsers() {
	    return userRepository.findAll();
	}
	public User getUserById(Long id) {
		return userRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("User not found"));
	}
	@Autowired
	private Jwtutil jwtUtil;
	public String loginUser(LoginRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
		        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
		    throw new IllegalArgumentException("Invalid email or password");
		}
		return jwtUtil.generateToken(request.getEmail());
	}

}

package com.taskmanager.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.dto.AuthResponse;
import com.taskmanager.dto.LoginRequest;
import com.taskmanager.dto.RegisterRequest;
import com.taskmanager.dto.UserResponse;
import com.taskmanager.entity.User;
import com.taskmanager.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@PostMapping("/register")
	public UserResponse registerUser(@RequestBody RegisterRequest request) {
		User user = userService.registerUser(request);
	    return new UserResponse(
	    		user.getId(),
	    		user.getName(),
	    		user.getEmail(),
	    		user.getRole().name()
	    		);
	}
	@GetMapping
	public List<User> getAllUsers() {
	    return userService.getAllUsers();
	}
	@GetMapping("/{id}")
	public UserResponse getUserById(@PathVariable Long id) {
	    User user = userService.getUserById(id);
	    return new UserResponse(
	    		user.getId(),
	    		user.getName(),
	    		user.getEmail(),
	    		user.getRole().name()
	    		);
	}

	@PostMapping("/login")
	public AuthResponse loginUser(@RequestBody LoginRequest request) {
		String token = userService.loginUser(request);
		return new AuthResponse(token);
	}
}

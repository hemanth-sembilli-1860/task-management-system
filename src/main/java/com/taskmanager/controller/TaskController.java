package com.taskmanager.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.dto.TaskRequest;
import com.taskmanager.entity.Priority;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.TaskStatus;
import com.taskmanager.service.TaskService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;


@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
	private TaskService taskService;
	@PostMapping
	public Task createTask(@Valid @RequestBody TaskRequest request,Authentication authentication) {
		return taskService.createTask(request,authentication.getName());
	}

	@GetMapping
	public List<Task> getAllTasks(Authentication authentication){
		return taskService.getAllTasks(authentication.getName());
	}
	@GetMapping("/{id}")
	public Task getTaskById(@PathVariable Long id,Authentication authentication){
		return taskService.getTaskById(id,authentication.getName());
	}

	@DeleteMapping("/{id}")
	public String deleteTask(@PathVariable Long id,Authentication authentication){
		taskService.deleteTask(id,authentication.getName());
		return "Task Deleted Successfully";
	}

	@GetMapping("/status/{status}")
	public List<Task> getAllTasksByStatus(@PathVariable("status") TaskStatus taskStatus,Authentication authentication){
		return taskService.getTaskByStatus(taskStatus,authentication.getName());
	}

	@GetMapping("/priority/{priority}")
	public List<Task> getAllTasksByStatus(@PathVariable Priority priority,Authentication authentication){
		return taskService.getTaskByPriority(priority,authentication.getName());
	}

	@PutMapping("/{id}")
	public Task updateTask(@PathVariable Long id,@Valid @RequestBody TaskRequest request,Authentication authentication) {
	    return taskService.updateTask(id, request,authentication.getName());
	}
	@PatchMapping("/{id}/complete")
	public Task markTaskComplete(@PathVariable Long id,Authentication authentication) {
	    return taskService.markTaskComplete(id,authentication.getName());
	}
}

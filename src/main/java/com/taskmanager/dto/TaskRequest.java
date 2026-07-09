package com.taskmanager.dto;

import java.time.LocalDateTime;

import com.taskmanager.entity.Priority;
import com.taskmanager.entity.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskRequest {
	@NotBlank
    private String title;
	@NotBlank
    private String description;
	@NotNull
    private TaskStatus status;
	@NotNull
    private Priority priority;
	@NotNull
    private LocalDateTime deadline;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public LocalDateTime getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

    
}
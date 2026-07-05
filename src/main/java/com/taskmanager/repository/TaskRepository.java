package com.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanager.entity.Priority;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.TaskStatus;

public interface TaskRepository extends JpaRepository<Task,Long> {
	List<Task> findByStatus(TaskStatus status);
	List<Task> findByPriority(Priority priority);
	List<Task> findByUserId(Long user_id);
	List<Task> findByUserEmail(String email);
}

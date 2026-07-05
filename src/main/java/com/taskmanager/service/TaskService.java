package com.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.entity.Priority;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.TaskStatus;
import com.taskmanager.entity.User;
import com.taskmanager.exception.TaskAccessDeniedException;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.repository.UserRepository;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;
	private Task validateTaskOwnership(Long taskId,String email) {
		Task task = taskRepository.findById(taskId).orElseThrow();

		if (!task.getUser().getEmail().equals(email)) {
			throw new TaskAccessDeniedException("Access Denied");
		}
		return task;
	}
	public Task createTask(Task task,String email) {
		User taskUser = userRepository.findById(task.getUser().getId()).orElseThrow();
		if (!task.getUser().getEmail().equals(email)) {
	        throw new TaskAccessDeniedException("Access denied");
	    }
		task.setUser(taskUser);
		return taskRepository.save(task);
	}

	public List<Task> getAllTasks(String email){

		return taskRepository.findByUserEmail(email);
	}

	public Task getTaskById(Long id, String email) {
	    return validateTaskOwnership(id, email);
	}

	public void deleteTask(Long id,String email) {
		Task task = validateTaskOwnership(id, email);
	    taskRepository.delete(task);
	}

	public List<Task> getTaskByStatus(TaskStatus status, String email){
	    return taskRepository.findByUserEmail(email)
	            .stream()
	            .filter(task -> task.getStatus() == status)
	            .toList();
	}

	public List<Task> getTaskByPriority(Priority priority, String email){
	    return taskRepository.findByUserEmail(email)
	            .stream()
	            .filter(task -> task.getPriority() == priority)
	            .toList();
	}


	public Task updateTask(Long id, Task updatedTask,String email) {
	    Task task = validateTaskOwnership(id, email);

	    task.setTitle(updatedTask.getTitle());
	    task.setDescription(updatedTask.getDescription());
	    task.setStatus(updatedTask.getStatus());
	    task.setPriority(updatedTask.getPriority());
	    task.setDeadline(updatedTask.getDeadline());

	    return taskRepository.save(task);
	}

	public Task markTaskComplete(Long id,String email) {
	    Task task = validateTaskOwnership(id, email);

	    task.setStatus(TaskStatus.COMPLETED);

	    return taskRepository.save(task);
	}
}

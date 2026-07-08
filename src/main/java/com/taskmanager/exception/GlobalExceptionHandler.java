package com.taskmanager.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	    @ExceptionHandler(TaskAccessDeniedException.class)
	    public Map<String, String> handleAccessDenied(TaskAccessDeniedException ex) {
	        Map<String, String> error = new HashMap<>();
	        error.put("message", ex.getMessage());
	        return error;
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public Map<String, String> handleValidationExceptions(
	            MethodArgumentNotValidException ex) {

	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors().forEach(error ->
	                errors.put(error.getField(), error.getDefaultMessage()));

	        return errors;
	    }
	    
	    @ExceptionHandler(IllegalArgumentException.class)
	    @ResponseStatus(HttpStatus.UNAUTHORIZED)
	    public Map<String, String> handleInvalidCredentials(IllegalArgumentException ex) {

	        Map<String, String> error = new HashMap<>();
	        error.put("message", ex.getMessage());

	        return error;
	    }
	    @ExceptionHandler(RuntimeException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public Map<String, String> handleNotFound(RuntimeException ex) {

	        Map<String, String> error = new HashMap<>();
	        error.put("message", ex.getMessage());

	        return error;
	    }
	    @ExceptionHandler(IllegalStateException.class)
	    @ResponseStatus(HttpStatus.CONFLICT)
	    public Map<String, String> handleDuplicateEmail(IllegalStateException ex) {

	        Map<String, String> error = new HashMap<>();
	        error.put("message", ex.getMessage());

	        return error;
	    }
}
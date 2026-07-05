package com.taskmanager.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskAccessDeniedException.class)
    public Map<String,String> handleAccessDenied(TaskAccessDeniedException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return error;
    }
}
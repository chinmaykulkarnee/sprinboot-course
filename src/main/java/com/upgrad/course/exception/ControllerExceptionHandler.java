package com.upgrad.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// TODO: Add appropriate annotation to mark this class as exception handler for multiple exceptions
@ControllerAdvice
public class ControllerExceptionHandler {

    // TODO: Handle TaskNotFoundException and return 404 response with exception message as body
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity handleTaskNotFound(TaskNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // TODO: Handle TaskAlreadyCompleteException and return 403 response with exception message as body
    @ExceptionHandler(TaskAlreadyCompleteException.class)
    public ResponseEntity handleTaskAlreadyCompleted(TaskAlreadyCompleteException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    // TODO: Handle TaskAlreadyCreatedException and return 409 response with exception message as body
    @ExceptionHandler(TaskAlreadyCreatedException.class)
    public ResponseEntity handleTaskAlreadyCreated(TaskAlreadyCreatedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}

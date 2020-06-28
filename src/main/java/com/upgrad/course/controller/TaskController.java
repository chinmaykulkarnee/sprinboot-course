package com.upgrad.course.controller;

import com.upgrad.course.dto.entity.TaskDto;
import com.upgrad.course.exception.TaskAlreadyCompleteException;
import com.upgrad.course.exception.TaskAlreadyCreatedException;
import com.upgrad.course.exception.TaskNotFoundException;
import com.upgrad.course.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "/tasks")
    // TODO: Add exception(s) thrown in the method signature with throws keyword
    public ResponseEntity getPostDetails(@RequestBody TaskDto taskDto) throws TaskAlreadyCreatedException {
        taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/tasks/{taskId}")
    // TODO: Add exception(s) thrown in the method signature with throws keyword
    public ResponseEntity getCommentsForPostWithLimit(@PathVariable Long taskId, @RequestBody TaskDto taskDto)
            throws TaskAlreadyCompleteException, TaskNotFoundException {
        taskService.updateTaskStatus(taskId, taskDto.getStatus());
        return ResponseEntity.ok().build();
    }
}

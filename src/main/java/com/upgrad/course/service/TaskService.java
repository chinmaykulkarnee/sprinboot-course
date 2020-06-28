package com.upgrad.course.service;

import com.upgrad.course.dto.entity.TaskDto;
import com.upgrad.course.entity.Status;
import com.upgrad.course.exception.TaskAlreadyCompleteException;
import com.upgrad.course.exception.TaskAlreadyCreatedException;
import com.upgrad.course.exception.TaskNotFoundException;

public interface TaskService {
    void createTask(TaskDto task) throws TaskAlreadyCreatedException;

    void updateTaskStatus(Long taskId, Status status) throws TaskAlreadyCompleteException, TaskNotFoundException;
}

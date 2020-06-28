package com.upgrad.course.service;

import com.upgrad.course.dto.entity.TaskDto;
import com.upgrad.course.entity.Status;
import com.upgrad.course.entity.TaskEntity;
import com.upgrad.course.exception.TaskAlreadyCompleteException;
import com.upgrad.course.exception.TaskAlreadyCreatedException;
import com.upgrad.course.exception.TaskNotFoundException;
import com.upgrad.course.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void createTask(TaskDto taskDto) throws TaskAlreadyCreatedException {
        try {
            // TODO: Use repository method to save the task
            taskRepository.save(taskDto.convertToEntity());
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            // TODO: Throw task already created exception when database save fails
            throw new TaskAlreadyCreatedException("Task already exists");
        }
    }

    @Override
    @Transactional
    public void updateTaskStatus(Long taskId, Status status) throws TaskAlreadyCompleteException, TaskNotFoundException {
        Optional<TaskEntity> mayBeTask = taskRepository.findById(taskId);
        if (mayBeTask.isPresent()) {
            TaskEntity taskEntity = mayBeTask.get();
            // TODO: Check the status of the task
            //  When status is COMPLETED, Throw task already completed exception
            //  Otherwise, Use repository method to update the status
            if (taskEntity.getStatus() == Status.COMPLETED)
                throw new TaskAlreadyCompleteException("Task is already Complete");
            else
                taskRepository.updateStatus(taskId, status);
        } else
            // TODO: Throw task not found when task is not present in the database
            throw new TaskNotFoundException("Task not found by given id");
    }

}

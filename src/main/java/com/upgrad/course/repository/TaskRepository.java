package com.upgrad.course.repository;

import com.upgrad.course.entity.Status;
import com.upgrad.course.entity.TaskEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

    @Modifying
    @Query("update TaskEntity t set t.status = ?2 where t.id = ?1")
    void updateStatus(Long taskId, Status status);
}

package com.taskflow.task_scheduler_service.infrastructure.repository;

import com.taskflow.task_scheduler_service.infrastructure.entity.TasksEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TasksRepository extends MongoRepository<TasksEntity, String> {

    List<TasksEntity> findByEventDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<TasksEntity> findByUserEmail(String userEmail);

}

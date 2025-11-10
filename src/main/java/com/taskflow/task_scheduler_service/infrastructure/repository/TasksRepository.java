package com.taskflow.task_scheduler_service.infrastructure.repository;

import com.taskflow.task_scheduler_service.infrastructure.entity.TasksEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends MongoRepository<TasksEntity, String> {


}

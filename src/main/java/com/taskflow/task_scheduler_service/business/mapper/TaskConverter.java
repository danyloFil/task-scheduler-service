package com.taskflow.task_scheduler_service.business.mapper;

import com.taskflow.task_scheduler_service.business.dto.TaskDTO;
import com.taskflow.task_scheduler_service.infrastructure.entity.TasksEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskConverter {

    TasksEntity convertToEntity(TaskDTO taskDTO);

    TaskDTO convertToDTO(TasksEntity tasksEntity);
}

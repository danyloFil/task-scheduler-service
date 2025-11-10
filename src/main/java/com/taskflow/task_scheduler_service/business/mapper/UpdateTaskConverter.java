package com.taskflow.task_scheduler_service.business.mapper;


import com.taskflow.task_scheduler_service.business.dto.TaskDTO;
import com.taskflow.task_scheduler_service.infrastructure.entity.TasksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UpdateTaskConverter {
    void updateTaskFromDto(TaskDTO dto, @MappingTarget TasksEntity tasksEntity);


}

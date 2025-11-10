package com.taskflow.task_scheduler_service.business;


import com.taskflow.task_scheduler_service.business.dto.TaskDTO;
import com.taskflow.task_scheduler_service.business.mapper.TaskConverter;
import com.taskflow.task_scheduler_service.infrastructure.enums.NotificationStatusEnum;
import com.taskflow.task_scheduler_service.infrastructure.repository.TasksRepository;
import com.taskflow.task_scheduler_service.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;
    private final TaskConverter taskConverter;
    private final JwtUtil jwtUtil;

    public TaskDTO createTask(String token, TaskDTO taskDTO) {
        String email = jwtUtil.extractTokenEmail(token.substring(7));
        taskDTO.setCreationDate(LocalDateTime.now());
        taskDTO.setNotificationStatusEnum(NotificationStatusEnum.PENDING);
        taskDTO.setUserEmail(email);
        // Implementation for creating a task
        return  taskConverter.convertToDTO(tasksRepository.
                save(taskConverter.convertToEntity(taskDTO)));
    }

}



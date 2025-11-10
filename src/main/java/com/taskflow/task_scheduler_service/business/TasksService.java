package com.taskflow.task_scheduler_service.business;


import com.taskflow.task_scheduler_service.business.dto.TaskDTO;
import com.taskflow.task_scheduler_service.business.mapper.TaskConverter;
import com.taskflow.task_scheduler_service.business.mapper.UpdateTaskConverter;
import com.taskflow.task_scheduler_service.infrastructure.entity.TasksEntity;
import com.taskflow.task_scheduler_service.infrastructure.enums.NotificationStatusEnum;
import com.taskflow.task_scheduler_service.infrastructure.exceptions.ResourceNotFoundExceptions;
import com.taskflow.task_scheduler_service.infrastructure.repository.TasksRepository;
import com.taskflow.task_scheduler_service.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;
    private final TaskConverter taskConverter;
    private final JwtUtil jwtUtil;
    private final UpdateTaskConverter updateTaskConverter;

    public TaskDTO createTask(String token, TaskDTO taskDTO) {
        String email = jwtUtil.extractTokenEmail(token.substring(7));
        taskDTO.setCreationDate(LocalDateTime.now());
        taskDTO.setNotificationStatusEnum(NotificationStatusEnum.PENDING);
        taskDTO.setUserEmail(email);
        // Implementation for creating a task
        return  taskConverter.convertToDTO(tasksRepository.
                save(taskConverter.convertToEntity(taskDTO)));
    }

    public List<TaskDTO> findByEventDateBetween
            (LocalDateTime startDate, LocalDateTime endDate) {
        return taskConverter.convertToDTOList(
                tasksRepository.findByEventDateBetween(startDate, endDate));
    }

    public List<TaskDTO> findByUserEmail(String token) {
        String userEmail = jwtUtil.extractTokenEmail(token.substring(7));
        return taskConverter.convertToDTOList(
                tasksRepository.findByUserEmail(userEmail));
    }

    public void deleteTaskById(String taskId) {
        try {
            tasksRepository.deleteById(taskId);
        } catch (ResourceNotFoundExceptions e) {
            throw new ResourceNotFoundExceptions("Task with ID '" + taskId + "' not found. Deletion failed.", e.getCause());
        }

    }

    public TaskDTO updateStatusNotification(NotificationStatusEnum status, String taskId) {
      try {
          TasksEntity taskEntity = tasksRepository.findById(taskId)
                  .orElseThrow(() -> new ResourceNotFoundExceptions("Task with ID '" + taskId + "' not found."));
          taskEntity.setNotificationStatusEnum(status);
          return taskConverter.convertToDTO(tasksRepository.save(taskEntity));
      } catch (ResourceNotFoundExceptions e) {
          throw new ResourceNotFoundExceptions("Task with ID '" + taskId + "' not found. Status update failed.", e.getCause());
      }
    }

    public TaskDTO updateTask(TaskDTO dto, String taskId) {
        try {
            TasksEntity existingTask = tasksRepository.findById(taskId)
                    .orElseThrow(() -> new ResourceNotFoundExceptions("Task with ID '" + taskId + "' not found."));
            updateTaskConverter.updateTaskFromDto(dto, existingTask);
            return taskConverter.convertToDTO(tasksRepository.save(existingTask));

        } catch (ResourceNotFoundExceptions e) {
            throw new ResourceNotFoundExceptions("Task with ID '" + taskId + "' not found. Update failed.", e.getCause());
        }
    }

}



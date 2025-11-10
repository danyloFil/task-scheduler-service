package com.taskflow.task_scheduler_service.controller;

import com.taskflow.task_scheduler_service.business.TasksService;
import com.taskflow.task_scheduler_service.business.dto.TaskDTO;
import com.taskflow.task_scheduler_service.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    public ResponseEntity <TaskDTO> createTask(@RequestBody TaskDTO taskDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tasksService.createTask(token, taskDTO));
    }

    @GetMapping("/task-between-dates")
    public ResponseEntity<List<TaskDTO>> getTasksBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return ResponseEntity.ok(tasksService.findByEventDateBetween(startDate, endDate));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasksByUserEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tasksService.findByUserEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTaskById(@RequestParam("id") String taskId) {
        tasksService.deleteTaskById(taskId);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/notification-status")
    public ResponseEntity<TaskDTO> updateNotificationStatus(@RequestParam("status") NotificationStatusEnum status,
                                                            @RequestParam("taskId") String taskId) {
       return  ResponseEntity.ok(tasksService.updateStatusNotification(status, taskId));

    }

    @PutMapping
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO,
                                              @RequestParam("id") String id) {
        return ResponseEntity.ok(tasksService.updateTask(taskDTO, id));
    }


}

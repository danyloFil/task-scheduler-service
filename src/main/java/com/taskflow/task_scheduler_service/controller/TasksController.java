package com.taskflow.task_scheduler_service.controller;

import com.taskflow.task_scheduler_service.business.TasksService;
import com.taskflow.task_scheduler_service.business.dto.TaskDTO;
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


}

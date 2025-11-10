package com.taskflow.task_scheduler_service.controller;

import com.taskflow.task_scheduler_service.business.TasksService;
import com.taskflow.task_scheduler_service.business.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksService tasksService;

    @PostMapping
    public ResponseEntity <TaskDTO> createTask(@RequestBody TaskDTO taskDTO, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tasksService.createTask(token, taskDTO));
    }

}

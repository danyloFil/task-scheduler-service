package com.taskflow.task_scheduler_service.infrastructure.entity;

import com.taskflow.task_scheduler_service.infrastructure.enums.NotificationStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Tasks")
public class TasksEntity {

    @Id
    private String id;
    private String taskName;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime eventDate;
    private String userEmail;
    private LocalDateTime updatedAt;
    private NotificationStatusEnum notificationStatusEnum;


}
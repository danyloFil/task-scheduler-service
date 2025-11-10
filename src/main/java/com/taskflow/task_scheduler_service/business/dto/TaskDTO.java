package com.taskflow.task_scheduler_service.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskflow.task_scheduler_service.infrastructure.enums.NotificationStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {

    private String id;
    private String taskName;
    private String description;
    private LocalDateTime creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime eventDate;
    private String userEmail;
    private LocalDateTime updateAt;
    private NotificationStatusEnum notificationStatusEnum;
}

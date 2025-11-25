package com.taskflow.task_scheduler_service.infrastructure.exceptions;

public class ResourceNotFoundException extends  RuntimeException {


    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

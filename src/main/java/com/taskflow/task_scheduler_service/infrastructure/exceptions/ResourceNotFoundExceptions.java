package com.taskflow.task_scheduler_service.infrastructure.exceptions;

public class ResourceNotFoundExceptions extends  RuntimeException {


    public ResourceNotFoundExceptions(String message) {
        super(message);
    }

    public ResourceNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}

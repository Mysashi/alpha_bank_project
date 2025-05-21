package com.example.project.service;


import jakarta.validation.Valid;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    public List getTasksByProjectId(Long projectId) {
        return null;
    }

    public Task createTask(Long projectId, @Valid Task createTaskRequest) {
        return null;
    }

    public Task getTaskById(Long taskId) {
        return null;
    }

    public Task updateTask(Long taskId, @Valid Task updateTaskRequest) {
        return null;
    }

    public void deleteTask(Long taskId) {

    }

    public List<Task> getTasksByFilter(String status, String priority, Long projectId) {
        return null;
    }
}

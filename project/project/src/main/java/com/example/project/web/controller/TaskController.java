package com.example.project.web.controller;

import com.example.project.domain.entity.Ticket;
import com.example.project.service.TaskService;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController

@RequestMapping("api/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;


    @GetMapping("{boardId}")
    public ResponseEntity<?> getTasksByBoardId(@PathVariable Long boardId) {
        try {
            List<Ticket> tasks = taskService.getTasksByBoardId(boardId);
            return ResponseEntity.ok(tasks);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a task for a project
    @PostMapping("projects/{projectId}/boards/{boardId}/tasks")
    public ResponseEntity<?> createTask(@PathVariable Long boardId, @PathVariable Long projectId, @Valid @RequestBody Ticket taskReq) {
        try {
            System.out.println(boardId);
            Ticket task = taskService.createTask(boardId, projectId, taskReq);
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("projects/{projectId}/board/{boardId}/tasks/{taskId}")
    public ResponseEntity<?> getTaskById(@PathVariable Long boardId, @PathVariable Long projectId, @PathVariable Long taskId) {
        try {
            Task task = taskService.getTaskById(taskId);
            return ResponseEntity.ok(task);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("projects/{projectId}/board/{boardId}/tasks/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId,  @PathVariable Long projectId, @Valid @RequestBody Task taskReq) {
        try {
            Task task = taskService.updateTask(taskId, taskReq);
            return ResponseEntity.ok(task);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Delete a task
    @DeleteMapping("projects/{projectId}/board/{boardId}/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId, @PathVariable Long projectId) {
        try {
            taskService.deleteTask(taskId);
            return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

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
    @PostMapping("{boardId}")
    public ResponseEntity<?> createTask(@PathVariable Long boardId, @Valid @RequestBody Ticket taskReq) {
        try {
            System.out.println(boardId);
            Ticket task = taskService.createTask(boardId, taskReq);
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

    @DeleteMapping("{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        Ticket task = taskService.deleteTask(taskId);
        return  new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("{taskId}")
    public ResponseEntity<?> getTaskById( @PathVariable Long taskId) {
        try {
            Ticket task = taskService.getTaskById(taskId);
            return ResponseEntity.ok(task);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @Valid @RequestBody Ticket taskReq) {
        try {
            Ticket task = taskService.updateTask(taskId, taskReq);
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

package com.example.project.service;


import com.example.project.domain.entity.Board;
import com.example.project.domain.entity.Ticket;
import com.example.project.domain.repository.BoardRepository;
import com.example.project.domain.repository.TicketRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getTasksByBoardId(Long boardId) {
        List<Ticket> tasks = ticketRepository.findByBoardId(boardId);
        System.out.println(tasks);
        return tasks;
    }

    @Autowired
    public BoardRepository boardRepository;

    public Ticket createTask(Long boardId,@Valid Ticket createTaskRequest) {
        Board board = boardRepository.findById(boardId).get();
        System.out.println(board.getBoardId());
        createTaskRequest.setBoard(board);
        createTaskRequest.setCreatedAt(LocalDateTime.now().toString());
        ticketRepository.save(createTaskRequest);
        return createTaskRequest;
    }

    public Ticket getTaskById(Long taskId) {
        return ticketRepository.findById(taskId).get();
    }

    public Ticket updateTask(Long taskId, @Valid Ticket updateTaskRequest) {
        Optional<Ticket> optionalTask = ticketRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Ticket task = optionalTask.get();
            task.setTicketType(updateTaskRequest.getTicketType());
            task.setName(updateTaskRequest.getName());
            task.setDescription(updateTaskRequest.getDescription());

            return ticketRepository.save(task);
        }
        return null;


    }

    public Ticket deleteTask(Long taskId) {
        Ticket ticket = ticketRepository.findById(taskId).get();
        ticketRepository.delete(ticket);
        return null;
    }

    public List<Task> getTasksByFilter(String status, String priority, Long projectId) {
        return null;
    }
}

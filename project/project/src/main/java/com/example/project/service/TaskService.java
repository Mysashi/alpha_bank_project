package com.example.project.service;


import com.example.project.domain.entity.Board;
import com.example.project.domain.entity.Ticket;
import com.example.project.domain.entity.TicketStatuses;
import com.example.project.domain.repository.BoardRepository;
import com.example.project.domain.repository.TicketRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public Ticket createTask(Long boardId, Long projectId, @Valid Ticket createTaskRequest) {
        Board board = boardRepository.findById(boardId).get();
        System.out.println(board.getBoardId());
        createTaskRequest.setBoard(board);
        ticketRepository.save(createTaskRequest);
        return createTaskRequest;
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

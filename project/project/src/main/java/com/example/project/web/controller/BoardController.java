package com.example.project.web.controller;

import com.example.project.domain.entity.Board;
import com.example.project.domain.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("{projectId}")
    public ResponseEntity<?> findBoardByProjectId(@PathVariable Long projectId) {
        List<Board> board = boardRepository.findByProjectId(projectId);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }


}

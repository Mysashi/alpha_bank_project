package com.example.project.domain.repository;

import com.example.project.domain.entity.Board;
import com.example.project.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository  extends JpaRepository<Board, Long> {

    List<Board> findByProjectId(Long projectId);
}

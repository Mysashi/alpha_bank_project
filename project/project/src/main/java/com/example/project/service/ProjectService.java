package com.example.project.service;

import com.example.project.domain.entity.Board;
import com.example.project.domain.entity.Project;
import com.example.project.domain.repository.BoardRepository;
import com.example.project.domain.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    BoardRepository boardRepository;


    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project createProjectRequest) {
        Project project = new Project();
        project.setName(createProjectRequest.getName());
        project.setDescription(createProjectRequest.getDescription());
        return projectRepository.save(project);
    }

    public void createBoard(Project project) {
        Board board = new Board();
        board.setName("default");
        board.setDescription("No description");
        board.setCreatedAt(LocalDateTime.now().toString());
        board.setProject(project);
        boardRepository.save(board);
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project not found with id: " + projectId));
    }


    public Project updateProject(Long projectId, Project updateProjectRequest) {
        Project project = getProjectById(projectId);
        project.setName(updateProjectRequest.getName());
        project.setDescription(updateProjectRequest.getDescription());
        return projectRepository.save(project);
    }

    void deleteProject(Long projectId) {
        Project project = getProjectById(projectId);
        projectRepository.delete(project);
    }
}

package com.example.project.service;

import com.example.project.domain.entity.Project;
import com.example.project.domain.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;


    public List<Project> getAllProjects() {
        return null;
    }

    public Project createProject(Project createProjectRequest) {
        Project project = new Project();
        project.setName(createProjectRequest.getName());
        project.setDescription(createProjectRequest.getDescription());
        return projectRepository.save(project);
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

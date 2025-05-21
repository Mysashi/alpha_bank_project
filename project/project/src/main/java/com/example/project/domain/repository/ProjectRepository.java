package com.example.project.domain.repository;

import com.example.project.domain.entity.Project;
import com.example.project.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

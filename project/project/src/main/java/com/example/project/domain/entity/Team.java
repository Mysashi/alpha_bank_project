package com.example.project.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {
    @Id
    private Long Id;
    private Long projectId;
    private String name;
    private String avatar;
    private String description;

    @ManyToMany
    private List<Project> projects;

    @OneToMany(mappedBy = "team")
    List<User> users;

    public Long getTeamId() {
        return Id;
    }

    public void setTeamId(Long teamId) {
        this.Id = teamId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}

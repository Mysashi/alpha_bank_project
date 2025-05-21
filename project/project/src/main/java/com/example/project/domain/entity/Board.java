package com.example.project.domain.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue
    private Long Id;
    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "project")
    private Project project;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;

    @OneToMany(mappedBy = "board")
    private List<TicketStatuses> ticketStatuses;

    public Long getBoardId() {
        return Id;
    }

    public void setBoardId(Long boardId) {
        this.Id = boardId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<TicketStatuses> getTicketStatuses() {
        return ticketStatuses;
    }

    public void setTicketStatuses(List<TicketStatuses> ticketStatuses) {
        this.ticketStatuses = ticketStatuses;
    }
}

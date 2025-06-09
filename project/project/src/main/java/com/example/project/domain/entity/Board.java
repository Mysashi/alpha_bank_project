package com.example.project.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "project")
    private Project project;

    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;

//    @OneToMany(mappedBy = "board")
//    private List<TicketStatuses> ticketStatuses;

    @JsonManagedReference
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();


    public List<Ticket> getTickets() {
        return tickets;
    }

    public Long getBoardId() {
        return id;
    }

    public void setBoardId(Long boardId) {
        this.id = boardId;
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

//    public List<TicketStatuses> getTicketStatuses() {
//        return ticketStatuses;
//    }
//
//    public void setTicketStatuses(List<TicketStatuses> ticketStatuses) {
//        this.ticketStatuses = ticketStatuses;
//    }
}

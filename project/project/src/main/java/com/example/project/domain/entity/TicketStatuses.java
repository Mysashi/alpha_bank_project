package com.example.project.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TicketStatuses {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "status")
    private List<Ticket> tickets;


    public Long getStatusId() {
        return Id;
    }

    public void setStatusId(Long statusId) {
        this.Id = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}

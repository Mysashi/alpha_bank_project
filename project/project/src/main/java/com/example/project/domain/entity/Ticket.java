package com.example.project.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private TicketStatuses status;
    private String name;
    private String description;
    private Long histTicketId;

    @ManyToOne
    @JoinColumn(name = "board")
    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Ticket() {

    }

    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketTypes ticketType;
    private String createdAt;

    @OneToMany(mappedBy = "ticket")
    private List<TicketHistory> ticketHistories;

    @ManyToMany
    @JoinTable(
            name = "Card_labels",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<Label> labels;

    @ManyToMany(mappedBy = "tickets")
    private List<User> users;

    public Long getCardId() {
        return Id;
    }

    public void setCardId(Long cardId) {
        this.Id = cardId;
    }

    public TicketStatuses getStatus() {
        return status;
    }

    public void setStatus(TicketStatuses status) {
        this.status = status;
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

    public Long getHistTicketId() {
        return histTicketId;
    }

    public void setHistTicketId(Long histTicketId) {
        this.histTicketId = histTicketId;
    }

    public TicketTypes getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketTypes ticketType) {
        this.ticketType = ticketType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<TicketHistory> getTicketHistories() {
        return ticketHistories;
    }

    public void setTicketHistories(List<TicketHistory> ticketHistories) {
        this.ticketHistories = ticketHistories;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

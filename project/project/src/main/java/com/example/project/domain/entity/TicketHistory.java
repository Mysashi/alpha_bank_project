package com.example.project.domain.entity;

import jakarta.persistence.*;

@Entity
public class TicketHistory {
    @Id
    @GeneratedValue
    private Long histTicketId;
    private String timestamp;
    private String columnFrom;
    private String columnTo;


    @ManyToOne
    @JoinColumn(name = "card_id")
    private Ticket ticket;

    public Long getHistTicketId() {
        return histTicketId;
    }

    public void setHistTicketId(Long histTicketId) {
        this.histTicketId = histTicketId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getColumnFrom() {
        return columnFrom;
    }

    public void setColumnFrom(String columnFrom) {
        this.columnFrom = columnFrom;
    }

    public String getColumnTo() {
        return columnTo;
    }

    public void setColumnTo(String columnTo) {
        this.columnTo = columnTo;
    }


    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

package com.example.project.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TicketTypes {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    private String color;


    @OneToMany(mappedBy = "ticketType")
    private List<Ticket> tickets;

    public Long getTicketTypeId() {
        return Id;
    }

    public void setTicketTypeId(Long ticketTypeId) {
        this.Id = ticketTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}


package com.example.project.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Label {
    @Id
    @GeneratedValue
    private Long Id;
    private String description;
    private String name;

    @ManyToMany(mappedBy = "labels")
    private List<Ticket> tickets;

    public Long getLabelId() {
        return Id;
    }

    public void setLabelId(Long labelId) {
        this.Id = labelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}

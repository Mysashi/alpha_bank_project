package com.example.project.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


@Entity
public class Project {
    @GeneratedValue
    @Id
    private Long Id;


    @ManyToMany
    private List<Team> teams;


    @OneToMany(mappedBy = "project")
    private List<Board> board;

    @NotBlank
    private String name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank String description) {
        this.description = description;
    }

    @NotBlank
    private String description;
}

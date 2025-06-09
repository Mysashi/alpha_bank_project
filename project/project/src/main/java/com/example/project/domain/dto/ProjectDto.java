package com.example.project.domain.dto;

import com.example.project.domain.entity.Board;
import com.example.project.domain.entity.Team;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


public class ProjectDto {


    private List<Team> teams;


    private String name;

    private Board board;


}

package com.crickmatch.dto;

import lombok.Data;

import java.util.List;

@Data
public class Team {
    private String name;
    private List<Player> players;
}

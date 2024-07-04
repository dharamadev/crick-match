package com.crickmatch.dto;

import lombok.Data;

@Data
public class Inning {
    private Team batting;
    private Team bowling;
    private Integer overs;
    private Integer runs;
}

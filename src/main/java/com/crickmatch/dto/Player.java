package com.crickmatch.dto;

import com.crickmatch.dto.constant.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private String name;
    private PlayerType playerType;
}

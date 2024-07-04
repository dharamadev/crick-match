package com.crickmatch.dto;

import com.crickmatch.dto.constant.ShotType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Shot {
    private Player player;
    private ShotType shot;
}

package com.crickmatch.dto;

import com.crickmatch.dto.constant.BowlType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Bowl {
    private Player player;
    private BowlType bowl;
}

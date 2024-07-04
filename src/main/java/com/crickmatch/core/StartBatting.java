package com.crickmatch.core;

import com.crickmatch.dto.Player;
import com.crickmatch.dto.Shot;
import com.crickmatch.dto.constant.ShotType;

import java.util.Random;

public class StartBatting {
    private final Player player;

    public StartBatting(Player player) {
        this.player = player;
    }

    public Shot play() {
        return new Shot(player, getType());
    }

    private ShotType getType() {
        Random random = new Random();
        ShotType[] types = ShotType.values();
        return types[random.nextInt(0, types.length-1)];
    }
}
